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
  @Input() customer: Customers = {customer_id: 0, name: '', address: '', phone: '', email: '', company: ''};
  @Input() isEditing = false;
  @Output() customerSave = new EventEmitter<Customers>();
  @Output() customerCancel = new EventEmitter<void>();

  formSubmitted = false;
  successMessage = '';

  saveCustomer(): void {
    this.formSubmitted = true; // Zet de vlag op true als de gebruiker Opslaan klikt

    if (this.isFormValid()) {
      this.customerSave.emit(this.customer);

      this.successMessage = this.isEditing
        ? 'Veranderingen opgeslagen'
        : 'Nieuwe klant aangemaakt';

      console.log(this.successMessage, this.customer);

      setTimeout(() => {
        this.successMessage = '';
      }, 1500);

      this.resetForm();
      this.formSubmitted = false; // Reset de validatie na succesvolle opslag
    }
  }

  cancelCustomer(): void {
    this.customerCancel.emit();
    this.resetForm();
    this.formSubmitted = false; // Reset de validatie bij annuleren
  }

  isFormValid(): boolean {
    if(
      this.customer.name == '' ||
      this.customer.email == '' ||
      this.customer.company == '' ||
      this.customer.phone == '' ||
      this.customer.address == ''
    ) {
      return false;
    } else {
      return true;
    }
  }

  resetForm(): void {
    this.customer = {
      customer_id: 0,
      name: '',
      email: '',
      company: '',
      phone: '',
      address: ''
    };
  }
}

