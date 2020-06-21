import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AddProjectDialogComponent} from './add-project-dialog/add-project-dialog.component';

@Component({
  selector: 'app-box',
  templateUrl: './box.component.html',
  styleUrls: ['./box.component.css']
})
export class BoxComponent implements OnInit {

  constructor(private matDialog: MatDialog) { }

  ngOnInit(): void {
    console.log('Box-Component called');
  }

  addProjectToList() {
  }

  openAddProjectDialog() {
    const addProjectDialogRef = this.matDialog.open(AddProjectDialogComponent, {
      width: 'auto',
      minWidth: '30vw'
    });

    addProjectDialogRef.afterClosed().subscribe(result => {
      alert('Dialog was closed');
    });
  }

}
