import {Component, OnInit} from '@angular/core';
import {ProjectService} from './project.service';
import {Project} from '../../db/entities/project';

@Component({
  selector: 'app-list-project',
  templateUrl: './list-project.component.html',
  styleUrls: ['./list-project.component.css']
})
export class ListProjectComponent implements OnInit {

  constructor(private service: ProjectService) {
  }

  private _projects: Project[] = [];

  get projects(): Project[] {
    return this._projects;
  }

  set projects(value: Project[]) {
    this._projects = value;
  }

  ngOnInit(): void {
    console.log('Project List Component called');
    this._projects = this.service.fetchData();
  }
}
