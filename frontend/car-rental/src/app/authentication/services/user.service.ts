import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private httpClient: HttpClient) { }

  private baseUrl = 'http://localhost:8080';

  getUsers():Observable<User[]> {
    console.log(this.httpClient.get<User[]>(`${this.baseUrl}/manage-user/list`))
    return this.httpClient.get<User[]>(`${this.baseUrl}/manage-user/list`);
  }

}
