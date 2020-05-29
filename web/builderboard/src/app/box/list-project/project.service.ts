import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Connection} from '../../util/connection';
import {Project} from '../../db/entities/project';

@Injectable()
export class ProjectService {

  private currentConnectionURL = null;

  constructor(private http: HttpClient) {
    console.log('ProjectService constructor is called');
    if (environment.production) {
      this.currentConnectionURL = Connection.prodURLConnection;
    } else {
      this.currentConnectionURL = Connection.localURLConnection;
    }
  }

  /**
   * returns the loaded projects of the server
   *
   * @return array of Project
   */
  public fetchData(): Project[] {
    /*let loadedObjects: Project[] = null;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept' : '*!/!*'
      })
    };
    this.http.get<Project[]>(this.currentConnectionURL + '/project', httpOptions).subscribe(
      res => (projects: Project[]) => {
        loadedObjects = projects;
      },
      err => console.log(err)
    );
    return loadedObjects;*/
    // TODO Comment out because it's not working, now the system will be mocked up to do first of all the basic implementation of the ui
    return [new Project(1, 12342, 'Test 1', Date.now(), Date.now(), false, 0, 0),
      new Project(2, 165485, 'Test 2', Date.now(), Date.now(), false, 0, 0),
      new Project(3, 213514, 'Test 3', Date.now(), Date.now(), false, 0, 0),
      new Project(4, 332152, 'Test 4', Date.now(), Date.now(), false, 0, 0)];
  }
}
