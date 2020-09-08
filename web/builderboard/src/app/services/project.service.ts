import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Connection} from '../util/connection';
import {Project} from '../db/entities/project';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Converter} from '../util/Converter';
import {SpinnerOverlayService} from './spinner-overlay.service';

@Injectable()
export class ProjectService {

  public isWaitingForNextFetch = false;
  private readonly currentConnectionURL = null;

  constructor(private http: HttpClient, private spinnerService: SpinnerOverlayService) {
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
    try {
      return this.http.get<Project[]>(this.currentConnectionURL + '/project', httpOptions).pipe(
        map((data: Project[]) => data.map(res => {
          return new Project(res.orderNumber, res.projectDescription, res.start,
            res.end, res.reminder, res.startReminder, res.endReminder, Converter.convertToNormalUserName(res.responsiblePersonName));
        })));
    } finally {
      this.spinnerService.hide();
      this.isWaitingForNextFetch = false;
    }
  }

  public create(project: Project) {
    this.http.post<Project>(this.currentConnectionURL + '/project', project).subscribe(res => {
    });
    this.isWaitingForNextFetch = true;
    this.spinnerService.show();
  }

  public update(project: Project) {
    this.http.put<Project>(this.currentConnectionURL + '/project', project).subscribe(res => {
    });
    this.isWaitingForNextFetch = true;
    this.spinnerService.show();
  }

  public delete(orderNumber: number): Observable<Project> {
    return this.http.delete<Project>(this.currentConnectionURL + '/project/' + orderNumber);
  }
}
