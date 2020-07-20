import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Connection} from '../../util/connection';
import {Project} from '../../db/entities/project';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

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
  public fetchData(): Observable<Project[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.get<Project[]>(this.currentConnectionURL + '/project', httpOptions).pipe(
      map((data: Project[]) => data.map(res => {
        return new Project(res.id, res.orderNumber, res.projectDescription, res.start,
          res.end, res.reminder, res.startReminder, res.endReminder, res.responsiblePerson);
        // return Object.assign(new Project(), res);
      })));
  }
}
