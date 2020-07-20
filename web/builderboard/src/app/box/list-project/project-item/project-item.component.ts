import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../db/entities/project';
import {MatDialog} from '@angular/material/dialog';
import {EditProjectDialogComponent} from '../../edit-project-dialog/edit-project-dialog.component';
import {User} from '../../../db/entities/user';

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styleUrls: ['./project-item.component.css']
})
export class ProjectItemComponent implements OnInit {

  @Input() project: Project;

  constructor(private matDialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  getNameOfUser(project: Project): string {
    return User.getNameOfUser(project.responsiblePerson.firstName, project.responsiblePerson.lastName);
  }

  openEditDialog() {
    const editProjectDialogRef = this.matDialog.open(EditProjectDialogComponent, {
      width: 'auto',
      minWidth: '30vw',
      data: this.project,
    });

    editProjectDialogRef.afterClosed().subscribe(result => {
        console.log('Result of Dialog');
        console.log(result);
      }
    );
  }

  deleteItem() {
    // TODO will implement in a later step of implementation
  }
}
