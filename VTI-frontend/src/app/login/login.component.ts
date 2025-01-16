import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service'; // Zorg dat deze service bestaat
import { UserLogin } from '../model/userLogin.model'; // Zorg dat dit model bestaat
import { AuthResponse } from '../model/authResponse.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username = '';
  password = '';

  constructor(private apiService: ApiService, private router: Router) {}

  login() {
    const userLogin: UserLogin = {
      username: this.username,
      password: this.password,
    };

    this.apiService.login(userLogin).subscribe(
      (response: AuthResponse) => {
        localStorage.setItem('token', response.token);
        console.log('Saved token:', response.token);

        const expiryTime = this.getTokenExpiry(response.token);
        this.startTokenExpiryTimer(expiryTime);

        const role = response.user.role;
        if (role === 'ADMIN') {
          this.router.navigate(['/dashboard']); // Admin gebruikers
        } else if (role === 'CUSTOMER') {
          window.location.href = 'https://andere-frontend-url.com'; // Klanten
        } else {
          alert('Onbekende rol: ' + role);
        }
      },
      (error) => {
        alert('Ongeldige inloggegevens: ' + error);
      }
    );
  }
  private getTokenExpiry(token: string): number {
    // JWT is "header.payload.signature"
    const payload = token.split('.')[1];
    const decoded = JSON.parse(atob(payload));
    // exp is in seconden sinds 1970, dus naar milliseconden omrekenen
    return decoded.exp * 1000;
  }
  private startTokenExpiryTimer(expiryTime: number) {
    const now = Date.now();
    const msUntilExpiry = expiryTime - now;
    if (msUntilExpiry > 0) {
      setTimeout(() => {
        // Token is nu verlopen
        localStorage.removeItem('token');
        alert('Je sessie is verlopen, log opnieuw in.');
        this.router.navigate(['/login']);
      }, msUntilExpiry);
    }
  }
}

