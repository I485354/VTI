import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Invoice } from '../model/invoices.model';
import { Customers } from '../model/customers.model';
import { InvoiceItem } from '../model/invoiceitems.model';
import { ApiService } from '../api.service';
import { FormsModule } from '@angular/forms';

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
    invoice_date: new Date(),
    due_date: new Date(),
    total_amount: 0,
    status: ''
  };

  customers: Customers[] = []; // Lijst van klanten
  invoiceItems: InvoiceItem[] = []; // Lijst van Factuuritems
  selectedCustomerId: number | null = null;
  selectedCustomer: Customers | null = null;
  totalBtwAmount: number = 0;

  constructor(private apiService: ApiService, private router: Router) {
  }

  ngOnInit(): void {
    const today = new Date();
    this.invoice.invoice_date = this.formatDate(today);

    const dueDate = new Date();
    dueDate.setMonth(today.getMonth() + 1);
    this.invoice.due_date = this.formatDate(dueDate);

    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });

    // Voeg een eerste leeg factuuritem toe
    this.addItem();
  }

  formatDate(date: Date): Date {
    const year = date.getFullYear();
    const month = date.getMonth();
    const day = date.getDate();
    return new Date(year, month,day);
  }

  onCustomerChange() {
    this.selectedCustomer = this.customers.find(customer => customer.id === this.selectedCustomerId) || null;

    if (this.selectedCustomer) {
      console.log(`Geselecteerde klant: ${this.selectedCustomer!.name}`);
    }
  }



  addItem() {
    this.invoiceItems.push({
      invoice_item_id: 0,
      invoice_id: this.invoice.id,
      product_id: 0,
      quantity: 1,
      unit_price: 0,
      btw: 0,
      total: 0
    });
  }
  removeItem(index: number) {
    this.invoiceItems.splice(index, 1);
    this.calculateTotals();
  }

  calculateTotals() {
    this.invoice.total_amount = 0;
    this.totalBtwAmount = 0;

    this.invoiceItems.forEach(item => {
      const itemTotal = item.quantity * item.unit_price;
      const itemBtwAmount = itemTotal * (item.btw / 100);

      // Totaal per item inclusief BTW
      item.total = itemTotal + itemBtwAmount;

      // Update totaalbedragen
      this.invoice.total_amount += item.total;
      this.totalBtwAmount += itemBtwAmount;
    });
  }

  onSubmit() {
    // Update de totaalprijs per item en bereken de totale factuurprijs
    this.invoiceItems.forEach(item => {
      item.total = item.quantity * item.unit_price;
    });
    this.calculateTotals();

    const invoiceToSend = {
      ...this.invoice,
      items: this.invoiceItems // Voeg de factuuritems toe aan de factuur
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
