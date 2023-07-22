import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private apiUrl = 'http://localhost:8080'; // Replace this with your Spring backend API URL
  private tokenKey = 'access_token'; // Key to store JWT token in browser storage

  constructor(private http: HttpClient) {}

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

  hasRoles(requiredRoles: string[]): boolean {
    const userRoles = this.getUserRoles();

    // Check if the user has all the required roles
    return requiredRoles.every((role) => userRoles.includes(role));
  }
}
function jwt_decode(token: string): any {
  throw new Error('Function not implemented.');
}

