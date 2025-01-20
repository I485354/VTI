import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserInfo } from '../model/userInfo';
import { Customers } from '../model/customer.model'; // Zorg dat dit model bestaat
import { ApiService } from '../api.service'; // Zorg dat deze service bestaat

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  @Input() user: UserInfo = { user_id: 0, username: '', role: '', customer_id: 0 };
  @Input() isEditing = false;
  @Output() userSave = new EventEmitter<UserInfo>();
  @Output() userCancel = new EventEmitter<void>();

  customers: Customers[] = []; // Lijst van klanten voor de dropdown

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    // Alleen klanten ophalen als de rol CUSTOMER is of tijdens initialisatie
    if (this.user.role === 'CUSTOMER' || !this.isEditing) {
      this.apiService.getCustomers().subscribe({
        next: (data: Customers[]) => {
          this.customers = data;
        },
        error: (error) => console.error('Fout bij ophalen klanten:', error),
      });
    }
  }

  onSave() {
    
    if (this.user.role === 'CUSTOMER' && !this.user.customer_id) {
      alert('Selecteer een klant voor de gebruiker.');
      return;
    }
    this.userSave.emit(this.user);
  }

  onCancel() {
    this.userCancel.emit();
  }
}

