import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AddProjectDialogComponent} from './add-project-dialog/add-project-dialog.component';
import {ErrorDialogComponent} from '../error-dialog/error-dialog.component';

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
        if (!result) {
          const errorDialogRef = this.matDialog.open(ErrorDialogComponent);
          errorDialogRef.componentInstance.errorMessage = 'Verantwortlicher konnte nicht ermittelt werden';
          errorDialogRef.componentInstance.errorDescription =
            'Veranwortlicher konnte aufgrund fehlender Informationen nicht ermittelt werden';

          errorDialogRef.afterClosed().subscribe(resultOfErrorDialog => {
            console.log('No successfully creation of user cause one error: result');
          });
        }
      }
    );
  }
}
