import { Component, OnInit } from '@angular/core';
import { ApiService } from '../apiService';
import { CustomerInfo } from '../model/CustomerInfo.model';
import { Invoice } from '../model/Invoice.model';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-customer',
  imports: [CommonModule],
  templateUrl: 'customer-page.component.html',
  styleUrl: 'customer-page.component.css',
})
export class CustomerPageComponent implements OnInit {
  customer: CustomerInfo | null = null;
  invoices: Invoice[] = [];
  customerId: number = 1;
  notLinkedToCustomer: boolean = false;

  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.customerId = +params['customerId'];
      setTimeout(() => {
        if (isNaN(this.customerId) || this.customerId <= 0) {
          console.error('Ongeldige customerId:', this.customerId);
          this.notLinkedToCustomer = true;
          return;
        }
        console.log('Customer ID na timeout:', this.customerId);
        this.fetchCustomerInfo();
        this.fetchInvoices();
      }, 1000);
    });
  }

  fetchCustomerInfo(): void {
    this.apiService.getCustomer(this.customerId).subscribe(
      (data: CustomerInfo) => {
        console.log('Klantinformatie:', data); // Debug output
        this.customer = data;
      },
      (error) => {
        console.error('Fout bij ophalen klantinformatie:', error);
      }
    );
  }

  fetchInvoices(): void {
    this.apiService.getInvoices(this.customerId).subscribe(
      (data: Invoice[]) => {
        console.log('Facturen:', data); // Debug output
        this.invoices = data;
      },
      (error) => {
        console.error('Fout bij ophalen facturen:', error);
      }
    );
  }


  viewInvoice(invoice: Invoice): void {
    alert(`Factuurdetails: \nFactuurnummer: ${invoice.invoice_number}`);
    // Voeg logica toe voor detailweergave
  }

  logout(): void {
    localStorage.removeItem('token');
    window.location.href = 'https://vti-frontend.vercel.app/login'; // Stuur gebruiker terug naar de loginpagina
  }
}
