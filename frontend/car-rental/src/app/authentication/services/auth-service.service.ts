import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, map } from 'rxjs';
import jwt_decode from 'jwt-decode';


@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private apiUrl = 'http://localhost:8080'; // Replace this with your Spring backend API URL
  private tokenKey = 'access_token'; // Key to store JWT token in browser storage

  constructor(private http: HttpClient , private router: Router) {}

  login(username: string, password: string) {
    //return this.httpClient.post<string>("http://localhost:9191/authenticate", request, {  responseType: 'text' as 'json' });
    return this.http.post<any>(`${this.apiUrl}/authenticate`, { username, password }).pipe(
      tap((response) => {
        console.log(response);
        if (response && response.accessToken) {
        console.log(response);

          // Store the JWT token in browser storage (localStorage)
          localStorage.setItem(this.tokenKey, response.accessToken);
        }
      })
    );
  }

  logout() {
    // Remove the JWT token from browser storage
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);

  }

  getToken() {
    // Get the JWT token from browser storage
    return localStorage.getItem(this.tokenKey);
  }

  isLoggedIn() {
    // Check if the user is logged in based on the presence of the JWT token
    return !!this.getToken();
  }

  getUserRoles(): string[] {
    const token = this.getToken();
    if (token) {
      const decodedToken: any = jwt_decode(token); // Decodes the JWT token
      console.log(decodedToken);
      const roles = decodedToken.roles || []; // Replace 'roles' with the actual property name for roles in your JWT
      return Array.isArray(roles) ? roles : [roles]; // Return roles as an array
    }
    return [];
  }

  getManagerPages(): Observable<String[]> {
    const roles = this.getUserRoles();
    return this.http.post<String[]>(`${this.apiUrl}/getPages`,roles).pipe(
      map(
        data=>{
          return data;
        }
      )
    )
  }


  hasRoles(requiredRoles: string[]): boolean {
    const userRoles = this.getUserRoles();
    console.log(userRoles)

    // Check if the user has all the required roles
    return requiredRoles.every((role) => userRoles.includes(role));
  }

  isTokenExpired(){
    const decodedToken: any = jwt_decode(this.getToken()!);
    const expirationTimeInMillis = decodedToken.exp * 1000;
    const currentTimestamp = new Date().getTime();

    if (currentTimestamp > expirationTimeInMillis) {
      console.log('Token has expired.');
      this.logout()
    } else {
      console.log('Token is still valid.');
    }

  }
}


