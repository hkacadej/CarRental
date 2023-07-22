import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isLoggedIn()) {
      // If the user is logged in (has a valid JWT token), check for required roles
      const requiredRoles = route.data['requiredRoles'] as string[]; // Get the required roles from the route's data

      if (this.authService.hasRoles(requiredRoles)) {
        // If the user has the required roles, allow access to the route
        return true;
      } else {
        // If the user doesn't have the required roles, redirect them to a different page or show an error
        this.router.navigate(['/unauthorized']);
        return false;
      }
    } else {
      // If the user is not logged in, redirect them to the login page
      this.router.navigate(['/login']);
      return false;
    }
  }
}
