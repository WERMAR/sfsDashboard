import {Component, Host, Input, OnInit} from '@angular/core';
import {Project} from '../../../db/entities/project';
import {MatDialog} from '@angular/material/dialog';
import {EditProjectDialogComponent} from '../../edit-project-dialog/edit-project-dialog.component';
import {User} from '../../../db/entities/user';
import {ListProjectComponent} from '../list-project.component';

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styleUrls: ['./project-item.component.css']
})
export class ProjectItemComponent implements OnInit {

  @Input() project: Project;

  constructor(@Host() private parent: ListProjectComponent, private matDialog: MatDialog) {
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
    console.log('delete item was triggered');
    this.parent.delete(this.project);
  }
}
