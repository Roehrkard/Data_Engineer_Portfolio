import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { TokenService } from './token.storage.service'; // Corrected import path
import { User } from '../models/user.model';
import { tap } from 'rxjs/operators';
import { jwtDecode } from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; // Backend API URL for user-related operations
  private authUrl = 'http://localhost:8080/api/authenticate'; // Backend API URL for authentication

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  authenticateUser(username: string, providedPin: string): Observable<{ authenticated: boolean, token?: string }> {
    return this.http.post<{ authenticated: boolean, token?: string }>(this.authUrl, { username, providedPin }).pipe(
      tap((response) => {
        if (response.token) {
          // Store the token using TokenService
          this.tokenService.setToken(response.token);
        }
      })
    );
  }

  getAllUsers(): Observable<User[]> {
    const token = this.tokenService.getToken();
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.get<User[]>(`${this.apiUrl}`, { headers });
  }

  getUsername(): Observable<string | null> {
    const token = this.tokenService.getToken();
    if (token) {
      const decodedToken = jwtDecode<{ sub: string }>(token);
      const username = decodedToken.sub;
      return of(username);
    }
    return of(null); // Token not available
  }

  logout(): void {
    this.tokenService.removeToken();
  }

  // Method to check if a user is authenticated
  isAuthenticated(): boolean {
    return !!this.tokenService.getToken();
  }
}
