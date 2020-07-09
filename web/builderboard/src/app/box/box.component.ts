import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AddProjectDialogComponent} from './add-project-dialog/add-project-dialog.component';

@Component({
  selector: 'app-box',
  templateUrl: './box.component.html',
  styleUrls: ['./box.component.css']
})
export class BoxComponent implements OnInit {

  constructor(private matDialog: MatDialog) {
  }

  ngOnInit(): void {
    console.log('Box-Component called');
  }

  addProjectToList() {
    // TODO implement in step 'functionality implementation of add project dialog'
  }

  openAddProjectDialog() {
    const addProjectDialogRef = this.matDialog.open(AddProjectDialogComponent, {
      width: 'auto',
      minWidth: '30vw'
    });

    addProjectDialogRef.afterClosed().subscribe(result => {
        console.log('Result of Dialog');
        console.log(result);
      }
    );
  }

}
