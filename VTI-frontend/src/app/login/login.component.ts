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
        // Sla de token op
        localStorage.setItem('token', response.token);

        // Controleer de rol en routeer
        const role = response.user.role;
        if (role === 'admin') {
          this.router.navigate(['/dashboard']); // Admin gebruikers
        } else if (role === 'customer') {
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
}

