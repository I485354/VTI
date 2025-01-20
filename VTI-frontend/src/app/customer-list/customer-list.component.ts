import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';
import { Customers } from '../model/customer.model';
import { CustomerFormComponent } from '../customer-form/customer-form.component';
import { UserFormComponent } from '../user-form/user-form.component'
import { UserInfo } from '../model/userInfo';


@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule, CustomerFormComponent, UserFormComponent],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})

export class CustomerListComponent implements OnInit {

  customers: Customers[] = [];
  selectedCustomer: Customers = { customer_id: 0, name: '', address: '', phone: '', email: '', company: ''};

  users: UserInfo[] = [];
  selectedUser: UserInfo = { user_id: 0, username: '', role: '', customer_id: 0 };

  isUserFormVisible = false;
  isFormVisible = false;
  isEditing = false;

  isViewingCustomers = true;
  isViewingUser = false;
  successMessage: string | null = null;
  confirmationMessage: string | null = null;


  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
    this.apiService.getUserInfo().subscribe((data: UserInfo[]) => {
      this.users = data;
    })
  }

  toggleView(viewCustomers: boolean,viewUser: boolean) {
    this.isViewingCustomers = viewCustomers;
    this.isViewingUser = viewUser;

  }

  onAddCustomer(): void {
    this.selectedCustomer = { customer_id: 0, name: '', address: '', phone: '', email: '', company: '' };
    this.isFormVisible = true;
  }

  onEditCustomer(customer: Customers): void {
    this.selectedCustomer = { ...customer };
    this.isEditing = true;
    this.isFormVisible = true;
    this.isViewingUser = false;
  }

  onEditUser(user: UserInfo): void {
    this.selectedUser = { ...user };
    this.isUserFormVisible = true;
    this.isViewingCustomers = false;
    this.isViewingUser = false
  }



  onSaveUser(user: UserInfo): void {
    if (user.user_id) {
      this.apiService.updateUser(user).subscribe(() => {
        this.successMessage = `${user.username} succesvol bijgewerkt!`;
        setTimeout(() => {
          this.successMessage = null;
          this.isUserFormVisible = false;
          this.isViewingCustomers = true; // Toon de gebruikerslijst
        }, 2000);
        this.loadUsers(); // Herlaad de gebruikerslijst
      });
    }
  }



  onSaveCustomer(customer: Customers): void {
    if (customer.customer_id) {
      this.apiService.updateCustomer(customer).subscribe(() => {
        this.successMessage = `${customer.name} succesvol bijgewerkt`;
        setTimeout(() => {
         this.successMessage = '';
          this.isFormVisible = false; // Verberg het formulier na 4 seconden
        }, 2000);
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
  onCancelUser(): void {
    this.isUserFormVisible = false; // Verberg het formulier
    this.isViewingCustomers = false; // Toon de gebruikerslijst weer
  }

  loadCustomers(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }

  loadUsers(): void {
    this.apiService.getUserInfo().subscribe((data: UserInfo[]) => {
      this.users = data;
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
        }, 2500);
        this.loadCustomers();
        this.cancelConfirmation();
      });
    }
  }

  cancelConfirmation(): void {
    this.confirmationMessage = null;

  }
}
