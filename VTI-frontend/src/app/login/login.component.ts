import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  /*email: string = '';
  password: string = '';

  constructor() {}

  onSubmit(): void {
    console.log('E-mail:', this.email);
    console.log('Wachtwoord:', this.password);
    // Verwerk inloggegevens of stuur naar een backend API
  }*/
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  login() {
    if (this.username === 'admin' && this.password === 'admin') {
      // Simuleer succesvolle login
      this.router.navigate(['/dashboard']);
    } else {
      alert('Ongeldige inloggegevens');
    }
  }
}
