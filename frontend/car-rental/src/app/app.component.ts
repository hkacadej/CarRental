import { AuthService } from './authentication/services/auth-service.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router ,private authService: AuthService) {}

  title = 'car-rental';

  isLoginPage(): boolean {
    console.log()
    return this.router.url === '/login';

  }
  logout(){
    this.authService.logout();
  }
}
