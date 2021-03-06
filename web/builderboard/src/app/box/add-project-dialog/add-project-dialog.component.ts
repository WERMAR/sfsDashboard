import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Project} from '../../db/entities/project';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UserService} from '../../services/user.service';
import {ProjectService} from '../../services/project.service';
import {DateValidator} from '../../services/datevalidator.service';

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
  selector: 'app-project-dialog',
  templateUrl: './add-project-dialog.component.html',
  styleUrls: ['./add-project-dialog.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class AddProjectDialogComponent implements OnInit {
  dateForm: FormGroup;
  submitted = false;
  public reminder = false;
  public times = [1, 2, 3, 4, 5];
  userNameFormControl = new FormControl();
  public usersNames: string[] = [];
  filteredOptions: Observable<string[]>;
  projectFromGroup: FormGroup = AddProjectDialogComponent.setupFormGroup();
  @Input() projectService: ProjectService;
  private _errorCreatingProject = false;

  constructor(public dialogRef: MatDialogRef<AddProjectDialogComponent>,
              private userService: UserService, private dialog: MatDialog,
              private formBuilder: FormBuilder) {
  }

  get f() {
    console.log(this.dateForm.controls.start.errors);
    return this.dateForm.controls;
  }

  private static setupFormGroup() {
    return new FormGroup({
      orderNumber: new FormControl(''),
      projectDescription: new FormControl(''),
      start: new FormControl(''),
      end: new FormControl(''),
      reminder: new FormControl(''),
      startReminder: new FormControl(''),
      endReminder: new FormControl(''),
      responsiblePersonName: new FormControl('')
    });
  }

  ngOnInit(): void {
    this.filteredOptions = this.userNameFormControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    this.fetchUserData();
    this.dateForm = this.formBuilder.group({
        start: [''],
        end: [''],
      },
      // use custom validator
      {validator: DateValidator('start', 'end', 'add')});
  }

  updateReminder() {
    this.reminder = !this.reminder;
  }

  hideDialog() {
    this.dialogRef.close(!this._errorCreatingProject);
  }

  createProject() {
    if (this.checkValues()) {
      const projectValue = this.projectFromGroup.value;

      const project = new Project(projectValue.orderNumber,
        projectValue.projectDescription,
        this.dateForm.value.start.format('YYYY-MM-DD'),
        this.dateForm.value.end.format('YYYY-MM-DD'),
        this.reminder,
        projectValue.startReminder,
        projectValue.endReminder,
        this.convertResponsiblePersonInFormat(this.userNameFormControl.value));

      if (!this._errorCreatingProject) {
        this.projectService.create(project);
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

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.usersNames.filter(user => user.toLowerCase().includes(filterValue));
  }

  private fetchUserData() {
    this.userService.fetchData().subscribe(res => {
      console.log(res);
      for (const user of res) {
        this.usersNames.push(user.firstName + ' ' + user.lastName);
      }
    });
  }
}
