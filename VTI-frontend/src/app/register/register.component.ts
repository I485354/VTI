
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { UserLogin } from '../model/userLogin.model'

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username = '';
  password = '';

  constructor(private router: Router, private apiService: ApiService) { }

  register() {
    // Maak een object van het model UserLogin
    const userLogin: UserLogin = {
      username: this.username,
      password: this.password
    };

    // Call de register API via ApiService
    this.apiService.register(userLogin).subscribe({
      next: () => {
        alert('Registratie geslaagd! Je kunt nu inloggen.');
        this.router.navigate(['/login']); // Verwijs de gebruiker naar de login-pagina
      },
      error: (error) => {
        alert('Registratie mislukt: ' + error.message);
      }
    });
  }
}
