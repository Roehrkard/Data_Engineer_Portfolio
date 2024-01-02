import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './services/user.service'; // Update the path as necessary

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'equipment-tracker-frontend';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  isLoggedIn(): boolean {
    return this.userService.isAuthenticated();
  }

  logout(): void {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
