import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  username !: string;
  password !: string;

  authRequest:any={
    "userName":this.username,
    "password":this.password
  };

  logout(){
    this.authService.logout();
  }
  login() {
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
