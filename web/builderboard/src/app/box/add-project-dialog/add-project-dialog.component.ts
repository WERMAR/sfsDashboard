import {Component} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Project} from '../../db/entities/project';
import {User} from '../../db/entities/user';


@Component({
  selector: 'app-add-project-dialog',
  templateUrl: './add-project-dialog.component.html',
  styleUrls: ['./add-project-dialog.component.css']
})
export class AddProjectDialogComponent {

  public project: Project = new Project(null, null, null, null, null, null, null, null, null);
  public times = [1, 2, 3, 4, 5];
  public users = this.createDummyUsers();

  constructor(public dialogRef: MatDialogRef<AddProjectDialogComponent>) {
  }

  updateReminder() {
    this.project.reminder = !this.project.reminder;
  }

  private createDummyUsers() {
    return Array.of(new User(1, 'Jens', 'Wernisch'),
      new User(1, 'Robert', 'Strohmeier'));
  }
}
