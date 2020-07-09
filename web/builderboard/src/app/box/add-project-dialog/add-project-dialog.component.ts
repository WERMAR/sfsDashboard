import {Component, Input, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Project} from '../../db/entities/project';
import {User} from '../../db/entities/user';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';


@Component({
  selector: 'app-project-dialog',
  templateUrl: './add-project-dialog.component.html',
  styleUrls: ['./add-project-dialog.component.css']
})
export class AddProjectDialogComponent implements OnInit {
  public project: Project = new Project(null, null, null, null, null, null, null, null, null);
  public times = [1, 2, 3, 4, 5];
  formControl = new FormControl();
  public usersNames: string[] = ['Jens Wernisch', 'Robert Strohmeier', 'Jenny Sturm', 'Siglinde Wernisch', 'Sandra Ederer'];
  filteredOptions: Observable<string[]>;

  constructor(public dialogRef: MatDialogRef<AddProjectDialogComponent>) {
  }

  ngOnInit(): void {
    this.filteredOptions = this.formControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }
  updateReminder() {
    this.project.reminder = !this.project.reminder;
  }

  hideDialog() {
    this.dialogRef.close();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.usersNames.filter(user => user.toLowerCase().includes(filterValue));
  }
}
