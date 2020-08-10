import {Component, OnInit} from '@angular/core';
import {ProjectService} from '../../services/project.service';
import {Project} from '../../db/entities/project';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmationDialogComponent} from '../../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-list-project',
  templateUrl: './list-project.component.html',
  styleUrls: ['./list-project.component.css']
})
export class ListProjectComponent implements OnInit {

  constructor(private service: ProjectService, private dialog: MatDialog) {

  }

  private _projects: Project[] = [];

  get projects(): Project[] {
    return this._projects;
  }

  loadData() {
    this.service.fetchData().subscribe(res => {
        this._projects = res;
        console.log(this._projects);
      },
      err => console.log(err)
    );
  }

  delete(project: Project) {
    this.openConfirmationDialog(project);
  }

  executeDeletion(project: Project) {
    if (!Number.isNaN(Number(project.orderNumber))) {
      this.service.delete(Number(project.orderNumber)).subscribe();
      this._projects.splice(this._projects.indexOf(project), 1);
    }
  }

  openConfirmationDialog(project: Project) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent);
    dialogRef.componentInstance.confirmMessage = 'Sind Sie sicher, dass das Projekt "' + project.projectDescription + '" gelöscht werden soll?';

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.executeDeletion(project);
      }
    });
  }

  ngOnInit(): void {
    this.loadData();
    setInterval(() => this.loadData(), 5000);
  }
}
