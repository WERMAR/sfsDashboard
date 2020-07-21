import {Component, OnInit} from '@angular/core';
import {ProjectService} from './project.service';
import {Project} from '../../db/entities/project';
import {User} from '../../db/entities/user';

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

  loadData() {
    this.service.fetchData().subscribe(res => {
        this._projects = res;
        for (const project of this._projects) {
          project.responsiblePerson = new User(project.responsiblePerson.id,
            project.responsiblePerson.firstName, project.responsiblePerson.lastName);
        }
        console.log(this._projects);
      },
      err => console.log(err)
    );
  }

  delete(project: Project) {
    this.service.delete(project.id).subscribe();
    this._projects.splice(this._projects.indexOf(project), 1);
  }

  ngOnInit(): void {

    console.log('Project List Component called');
    this.loadData();
    setInterval(() => this.loadData(), 5000);
  }
}
