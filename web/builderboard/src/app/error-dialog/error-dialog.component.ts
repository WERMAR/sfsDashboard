import {Component, Input, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
})
export class ErrorDialogComponent implements OnInit {

  @Input() errorMessage: string;
  @Input() errorDescription: string;

  constructor(private dialogRef: MatDialogRef<ErrorDialogComponent>) { }

  ngOnInit(): void {
  }

  accept() {
    this.dialogRef.close(true);
  }
}
