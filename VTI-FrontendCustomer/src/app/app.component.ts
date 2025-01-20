import { Component, OnInit } from '@angular/core';
import { ApiService } from './apiService';
import { CustomerInfo } from './model/CustomerInfo.model';
import { Invoice } from './model/Invoice.model';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  customer: CustomerInfo | null = null;
  invoices: Invoice[] = [];
  customerId: number = 1; // Vervang dit met de ingelogde klant-ID

  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.customerId = +params['customerId']; // Haal de customerId uit de URL
      console.log('Customer ID:', this.customerId);
    });
    this.fetchCustomerInfo();
    this.fetchInvoices();
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
    window.location.href = 'https//vti-frontend.vercel.appy/login'; // Stuur gebruiker terug naar de loginpagina
  }
}
