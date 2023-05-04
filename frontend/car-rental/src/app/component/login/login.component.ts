import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username !: string;
  password !: string;

  constructor(private http: HttpClient) { }

  onSubmit() {
    const credentials = { username: this.username, password: this.password };
    this.http.post('http://localhost:8080/login', credentials).subscribe(
      response => console.log('Login successful'),
      error => console.error('Login failed', error)
    );
  }
}
