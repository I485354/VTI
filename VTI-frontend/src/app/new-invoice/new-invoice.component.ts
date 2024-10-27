import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router} from '@angular/router';
import { Invoice } from '../model/invoices.model';
import { Customers} from '../model/customers.model';
import {ApiService} from '../api.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-new-invoice',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './new-invoice.component.html',
  styleUrl: './new-invoice.component.css'
})
export class NewInvoiceComponent implements OnInit {
  invoice: Invoice = {
    id: 0,
    customer_id: 0,
    invoice_date: new Date(),  // Initieer als Date object
    due_date: new Date(),      // Initieer als Date object
    total_amount: 0,
    status: ''
  };
  customers: Customers[] = []; // Lijst van klanten
  constructor(private apiService: ApiService, private router: Router) {
  }

  ngOnInit(): void {
    // Huidige datum instellen
    const today = new Date();
    this.invoice.invoice_date = this.formatDate(today);

    // Vervaldatum instellen op één maand later
    const dueDate = new Date();
    dueDate.setMonth(today.getMonth() + 1);
    this.invoice.due_date = this.formatDate(dueDate);

    // Klanten ophalen van de API
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }
  formatDate(date: Date): Date {
    const year = date.getFullYear();
    const month = date.getMonth();
    const day = date.getDate();
    return new Date(year, month,day);
  }

  onSubmit() {

    const invoiceToSend = {
      ...this.invoice
    };

    this.apiService.addInvoice(invoiceToSend).subscribe(
      response => {
        console.log('Factuur aangemaakt:', response);
        this.router.navigate(['/invoices']);
      },
      error => {
        console.error('Er is een fout opgetreden:', error);
      }
    );
  }
}
