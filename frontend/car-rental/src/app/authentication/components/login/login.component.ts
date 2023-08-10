import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import { Router } from '@angular/router';
import * as anime from 'animejs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private authService: AuthService,
              private router: Router,
              private elementRef: ElementRef,
              private renderer: Renderer2) {}

  username !: string;
  password !: string;

  authRequest:any={
    "userName":this.username,
    "password":this.password
  };

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  login() {
    console.log(this.username);
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
          // Login successful, you can redirect the user to a protected route here
          this.router.navigate(['/cars']);
      },
      (error) => {
        // Handle login error, e.g., display an error message
      }
    );
  }
}
