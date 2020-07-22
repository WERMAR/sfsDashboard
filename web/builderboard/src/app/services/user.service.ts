import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Connection} from '../util/connection';
import {User} from '../db/entities/user';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

  private readonly currentConnectionURL = null;

  constructor(private http: HttpClient) {
    console.log('ProjectService constructor is called');
    if (environment.production) {
      this.currentConnectionURL = Connection.prodURLConnection;
    } else {
      this.currentConnectionURL = Connection.localURLConnection;
    }
  }

  fetchData(): Observable<User[]> {
    return this.http.get<User[]>(this.currentConnectionURL + '/user/all').pipe(
      map((data: User[]) => data.map(res => {
        return new User(res.id, res.firstName, res.lastName);
      })));
  }
}
