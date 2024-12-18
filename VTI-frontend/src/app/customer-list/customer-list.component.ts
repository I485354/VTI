import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';
import { Customers } from '../model/customer.model';
import { CustomerFormComponent } from '../customer-form/customer-form.component';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule, CustomerFormComponent],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})

export class CustomerListComponent implements OnInit {

  customers: Customers[] = [];
  selectedCustomer: Customers = { customer_id: 0, name: '', address: '', phone: '', email: '', company: ''};
  isFormVisible = false;
  isEditing = false;
  successMessage: string | null = null;
  confirmationMessage: string | null = null;


  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }
  onAddCustomer(): void {
    this.selectedCustomer = { customer_id: 0, name: '', address: '', phone: '', email: '', company: '' };
    this.isFormVisible = true;
  }

  onEditCustomer(customer: Customers): void {
    this.selectedCustomer = { ...customer };
    this.isEditing = true;// Maak een kopie om ongewenste wijzigingen te voorkomen
    this.isFormVisible = true;
  }

  onSaveCustomer(customer: Customers): void {
    if (customer.customer_id) {
      this.apiService.updateCustomer(customer).subscribe(() => {
        this.successMessage = `${customer.name} succesvol bijgewerkt`;
        setTimeout(() => {
         this.successMessage = '';
          this.isFormVisible = false; // Verberg het formulier na 4 seconden
        }, 4000);
        this.loadCustomers();
      });
    } else {
      this.apiService.addCustomer(customer).subscribe(() => {
        this.successMessage = `${customer.name} succesvol toegevoegd!`;
        setTimeout(() => {
          this.successMessage = '';
          this.isFormVisible = false; // Verberg het formulier na 4 seconden
        }, 4000);
        this.loadCustomers();

      });
    }
  }

  onCancel(): void {
    this.isFormVisible = false;
  }

  loadCustomers(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }

  requestDeleteCustomer(customer: Customers): void {
    this.selectedCustomer = customer;
    this.confirmationMessage = `Weet je zeker dat je ${customer.name} wilt verwijderen?`;
  }

  confirmDelete(): void {
    if (this.selectedCustomer) {
      this.apiService.deleteCustomer(this.selectedCustomer.customer_id).subscribe(() => {
        this.successMessage = `${this.selectedCustomer?.name} succesvol verwijderd!`;
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
        this.loadCustomers();
        this.cancelConfirmation();
      });
    }
  }

  cancelConfirmation(): void {
    this.confirmationMessage = null;

  }
}
