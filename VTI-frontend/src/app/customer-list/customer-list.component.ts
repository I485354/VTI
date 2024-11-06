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
    this.selectedCustomer = { ...customer }; // Maak een kopie om ongewenste wijzigingen te voorkomen
    this.isFormVisible = true;
  }

  onSaveCustomer(customer: Customers): void {
    if (customer.customer_id) {
      this.apiService.updateCustomer(customer).subscribe(() => {
        this.loadCustomers();
        this.isFormVisible = false;
      });
    } else {
      this.apiService.addCustomer(customer).subscribe(() => {
        this.loadCustomers();
        this.isFormVisible = false;
      });
    }
  }

  onCancel(): void {
    this.isFormVisible = false;
  }

  private loadCustomers(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }
}
