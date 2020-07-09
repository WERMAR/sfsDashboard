import {Component, Inject, Input, OnInit} from '@angular/core';
import {Project} from '../../db/entities/project';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-edit-project-dialog',
  templateUrl: './edit-project-dialog.component.html',
  styleUrls: ['./edit-project-dialog.component.css']
})
export class EditProjectDialogComponent implements OnInit {

  @Input() editMode: boolean;
  public times = [1, 2, 3, 4, 5];
  formControl = new FormControl();
  public usersNames: string[] = ['Jens Wernisch', 'Robert Strohmeier', 'Jenny Sturm', 'Siglinde Wernisch', 'Sandra Ederer'];
  filteredOptions: Observable<string[]>;

  constructor(private dialogRef: MatDialogRef<EditProjectDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: Project) {
    console.log(data);
  }

  ngOnInit(): void {
    if (this.data.responsiblePerson != null) {
      this.formControl.setValue(this.data.responsiblePerson.firstName + ' ' + this.data.responsiblePerson.lastName);
    }
    this.filteredOptions = this.formControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  updateReminder() {
    this.data.reminder = !this.data.reminder;
  }

  hideDialog() {
    this.dialogRef.close();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.usersNames.filter(user => user.toLowerCase().includes(filterValue));
  }

}
