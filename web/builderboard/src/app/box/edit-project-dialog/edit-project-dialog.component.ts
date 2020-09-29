import {Component, Inject, Input, OnInit} from '@angular/core';
import {Project} from '../../db/entities/project';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UserService} from '../../services/user.service';
import {ProjectService} from '../../services/project.service';
import {DateValidator} from '../../services/datevalidator.service';
import * as moment from 'moment';
import {Moment} from 'moment';

export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'LL',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};


@Component({
  selector: 'app-edit-project-dialog',
  templateUrl: './edit-project-dialog.component.html',
  styleUrls: ['./edit-project-dialog.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class EditProjectDialogComponent implements OnInit {
  dateForm: FormGroup;
  submitted = false;
  @Input() editMode: boolean;
  public times = [1, 2, 3, 4, 5];
  public usersNames: string[] = [];
  filteredOptions: Observable<string[]>;
  projectFromGroup: FormGroup;
  userNameFormControl = new FormControl();
  private _errorCreatingProject = false;

  constructor(private dialogRef: MatDialogRef<EditProjectDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Project, private userService: UserService,
              private projectService: ProjectService,
              private formBuilder: FormBuilder) {
  }

  get f() {
    return this.dateForm.controls;
  }

  ngOnInit(): void {
    this.setupFormGroup();
    if (this.data.responsiblePersonName != null) {
      this.userNameFormControl.setValue(this.data.responsiblePersonName);
    }
    this.filteredOptions = this.userNameFormControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    this.fetchUserData();
    this.dateForm = this.formBuilder.group({
        start: [this.data.start],
        end: [this.data.end],
      },
      // use custom validator
      {validator: DateValidator('start', 'end')});
  }

  updateReminder() {
    this.data.reminder = !this.data.reminder;
  }

  hideDialog() {
    this.dialogRef.close();
  }

  updateProject() {

    if (this.checkValues()) {
      const projectValue = this.projectFromGroup.value;
      console.log(projectValue);
      const project = new Project(projectValue.orderNumber,
        projectValue.projectDescription,
        this.dateForm.value.start.format('YYYY-MM-DD'),
        this.dateForm.value.end.format('YYYY-MM-DD'),
        this.data.reminder,
        projectValue.startReminder,
        projectValue.endReminder,
        this.convertResponsiblePersonInFormat(this.userNameFormControl.value));

      if (!this._errorCreatingProject) {
        this.projectService.update(project);
      }
      this.hideDialog();
    }
  }

  checkValues() {
    this.submitted = true;
    // Returns false if form is invalid
    return !this.dateForm.invalid;
  }

  private convertResponsiblePersonInFormat(responsiblePersonName: string) {
    const splitNameArray = responsiblePersonName.split(' ');
    if (splitNameArray.length > 1) {
      return splitNameArray[0] + '@' + splitNameArray[1];
    } else {
      this._errorCreatingProject = true;
    }
  }

  private setupFormGroup() {
    this.projectFromGroup = new FormGroup({
      orderNumber: new FormControl(''),
      projectDescription: new FormControl(''),
      start: new FormControl(''),
      end: new FormControl(''),
      reminder: new FormControl(''),
      startReminder: new FormControl(''),
      endReminder: new FormControl(''),
      responsiblePersonName: new FormControl('')
    });

    const startDate: Moment = moment(this.data.start, 'YYYY-MM-DD');
    const endDate: Moment = moment(this.data.end, 'YYYY-MM-DD');
    this.projectFromGroup.setValue(
      {
        orderNumber: this.data.orderNumber,
        projectDescription: this.data.projectDescription,
        start: startDate,
        end: endDate,
        reminder: this.data.reminder,
        startReminder: this.data.startReminder,
        endReminder: this.data.endReminder,
        responsiblePersonName: this.data.responsiblePersonName
      }
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.usersNames.filter(user => user.toLowerCase().includes(filterValue));
  }

  private fetchUserData() {
    this.userService.fetchData().subscribe(res => {
      for (const user of res) {
        this.usersNames.push(user.firstName + ' ' + user.lastName);
      }
    });
  }
}
