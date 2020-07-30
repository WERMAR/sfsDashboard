import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Project} from '../../db/entities/project';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {UserService} from '../../services/user.service';
import {User} from '../../db/entities/user';
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

export class ProjectTransfer {
  orderNumber: string;
  projectDescription: string;
  start: Moment;
  end: Moment;
  reminder: boolean;
  startReminder: number;
  endReminder: number;
  responsiblePersonName: string;
}

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
  public projectTransfer: ProjectTransfer = new ProjectTransfer();
  public times = [1, 2, 3, 4, 5];
  formControl = new FormControl();
  public usersNames: string[] = [];
  filteredOptions: Observable<string[]>;

  constructor(public dialogRef: MatDialogRef<AddProjectDialogComponent>,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.filteredOptions = this.formControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    this.fetchUserData();
  }

  updateReminder() {
    this.projectTransfer.reminder = !this.projectTransfer.reminder;
  }

  hideDialog() {
    this.dialogRef.close();
  }

  createProject() {
    console.log(this);
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
