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
  //@Input() customer: Customers | null = null;
  @Input() customer: Customers = { customer_id: 0, name: '', address: '', phone: '', email: '', company: ''};
  @Output() save = new EventEmitter<Customers>();
  @Output() cancel = new EventEmitter<void>();

  onSave(): void {
    this.save.emit(this.customer);
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
