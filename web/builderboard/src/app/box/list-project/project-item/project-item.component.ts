import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../db/entities/project';

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styles: []
})
export class ProjectItemComponent implements OnInit {

  @Input() project: Project;

  constructor() { }

  ngOnInit(): void {
  }

}
