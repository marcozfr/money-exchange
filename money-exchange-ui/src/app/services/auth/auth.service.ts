import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { User } from '../../model/auth/user';

import { SERVER_API_URL } from '../../app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) : Observable<User> {
      return this.http.post<User>(
          `${SERVER_API_URL}/auth`, { username: username, password: password })
          .pipe(tap(user => {
                const currentUser = JSON.stringify(user);
                sessionStorage.setItem('currentUser', currentUser);
          }));
  }

  logout() {
      sessionStorage.removeItem('currentUser');
  }

}
