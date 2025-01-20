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
  notificationMessage: string | null = null;
  notificationType: 'success' | 'error' | null = null;

  constructor(private apiService: ApiService, private router: Router) {}

  login() {
    if (!this.username.trim()) {
      this.showNotification('Gebruikersnaam is verplicht.', 'error');
      return;
    } else if(!this.password.trim()) {
      this.showNotification('wachtwoord is verplicht.', 'error');
      return;
    }
    const userLogin: UserLogin = {
      username: this.username,
      password: this.password,
    };

    this.apiService.login(userLogin).subscribe(
      (response: AuthResponse) => {
        localStorage.setItem('token', response.token);

        const expiryTime = this.getTokenExpiry(response.token);
        this.startTokenExpiryTimer(expiryTime);

        const role = response.user.role;
        if (role === 'ADMIN') {
          this.router.navigate(['/dashboard']);
        } else if (role === 'CUSTOMER') {
          const customerId = response.user.customer_id;
         // window.location.href = `http://localhost:64404?customerId=${customerId}`;
         window.location.href = `https://vti-customer.vercel.app?customerId=${customerId}`; // Klanten
        } else {
          this.showNotification('Onbekende rol: ' + role, 'error');
        }
      },
      (error) => {
        if (error.status === 401) {
          this.showNotification(`Ongeldige inloggegevens voor ${this.username}`, 'error');
        } else {
          this.showNotification('Er is iets misgegaan. Probeer het later opnieuw.', 'error');
        }
      }
    );
  }

  private getTokenExpiry(token: string): number {

    const payload = token.split('.')[1];
    const decoded = JSON.parse(atob(payload));
    return decoded.exp * 1000;
  }
  private startTokenExpiryTimer(expiryTime: number) {
    const now = Date.now();
    const msUntilExpiry = expiryTime - now;
    if (msUntilExpiry > 0) {
      setTimeout(() => {
        // Token is nu verlopen
        localStorage.removeItem('token');
        this.showNotification('Je sessie is verlopen, log opnieuw in.', 'error');
        this.router.navigate(['/login']);
      }, msUntilExpiry);
    }
  }
  private showNotification(message: string, type: 'success' | 'error'): void {
    this.notificationMessage = message;
    this.notificationType = type;
    setTimeout(() => {
      this.notificationMessage = null;
      this.notificationType = null;
    }, 5000);
  }
}

