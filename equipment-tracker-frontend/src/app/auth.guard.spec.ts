import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    if (!this.isAuthenticated()) {
      // User is not authenticated, redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
    // User is authenticated, check token expiration
    if (this.isTokenExpired()) {
      // Token is expired, redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
    // User is authenticated and token is not expired, allow access to the route
    return true;
  }

  isAuthenticated(): boolean {
    // Implement logic to check if the user is authenticated
    // For example, check if a token exists in local storage
    return !!localStorage.getItem('authToken');
  }

  isTokenExpired(): boolean {
    // Implement logic to check if the token is expired
    // You can compare the token's expiration date with the current date
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      const tokenData = JSON.parse(atob(authToken.split('.')[1]));
      const expirationDate = new Date(tokenData.exp * 1000); // Convert to milliseconds
      return expirationDate <= new Date();
    }
    return true; // No token or invalid token
  }
}
