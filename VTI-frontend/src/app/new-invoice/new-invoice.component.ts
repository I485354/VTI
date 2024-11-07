import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Invoice } from '../model/invoices.model';
import { Customers } from '../model/customer.model';
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
    invoice_id: 0,
    customer_id: 0,
    invoice_date: new Date(),
    due_date: new Date(),
    total_btw: 0,
    total_amount: 0,
    status: ''
  };

  customers: Customers[] = []; // Lijst van klanten
  invoiceItems: InvoiceItem[] = []; // Lijst van Factuuritems
  selectedCustomerId= 0; // Start met 0 in plaats van null
  selectedCustomer: Customers = { customer_id: 0, name: '', company: '', address: '', email: '', phone: '' };
  totalBtwAmount = 0;

  // Lege klant als standaardwaarde

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
    this.selectedCustomer = this.customers.find(customer => customer.customer_id === +this.selectedCustomerId) ||
      { customer_id: 0, name: '', company: '', address: '', email: '', phone: '' };
    this.invoice.customer_id = this.selectedCustomer.customer_id;
  }

 


  addItem() {
    this.invoiceItems.push({
      invoice_item_id: 0,
      invoice_id: this.invoice.invoice_id,
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
      this.invoice.total_btw = itemBtwAmount;

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
    console.log(this.invoice);
    console.log(invoiceToSend);

    console.log('Verzonden factuur:', invoiceToSend); // Voeg deze log toe om de payload te controleren

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
