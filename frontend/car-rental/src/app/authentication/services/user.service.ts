import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { User } from '../common/user';
import { UserDto } from '../common/user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private httpClient: HttpClient) { }

  private baseUrl = 'http://localhost:8080';

  getUsers():Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseUrl}/manage-user/list`);
  }

  addUser(user: UserDto):Observable<any>{
    return this.httpClient.post<User>(`${this.baseUrl}/manage-user/add`,user)
    .pipe(
      map(response => {
        console.log(response);
        return response;
      })
    );
  }
}
