import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Customers } from '../model/customer.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.css'
})

export class CustomerFormComponent {
  @Input() customer: Customers = { customer_id: 0, name: '', address: '', phone: '', email: '', company: ''};
  @Input() isEditing: boolean = false;
  @Output() customerSave = new EventEmitter<Customers>();
  @Output() customerCancel = new EventEmitter<void>();

  successMessage: string = ''; // Variabele voor succesbericht

  saveCustomer(): void {
    this.customerSave.emit(this.customer);
    this.successMessage = 'Changes saved'; // Stel succesbericht in
    setTimeout(() => {
      this.successMessage = ''; // Verwijder bericht na 3 seconden
    }, 3000);
    console.log(this.customer)// Tijd in milliseconden
  }

  cancelCustomer(): void {
    this.customerCancel.emit();
  }
}
