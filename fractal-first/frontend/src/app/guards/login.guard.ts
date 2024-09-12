import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ROUTE } from '../constants/route';

@Injectable({
  providedIn: 'root',
})
/**
 * Guard that checks if the user is logged in before allowing access to a route.
 */
export class LoginGuard {
  constructor(private router: Router) {}

  /**
   * Determines if the user is allowed to access the route.
   * @returns true if the user is logged in, false otherwise and redirect to login route.
   */
  canActivate(): boolean {
    if (localStorage.getItem('token') && localStorage.getItem('token') !== 'undefined') {
      return true;
    } else {
      this.router.navigate([ROUTE.LOGIN]);
      return false;
    }
  }
}
