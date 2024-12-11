import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { Invoice } from '../model/invoices.model';
import { Customers } from '../model/customer.model';
import { InvoiceItem } from '../model/invoiceitems.model';
import { Products } from '../model/products.model';
import { Car } from '../model/car.model';

import { ApiService } from '../api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';



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
    car_id: 0,
    invoice_date: new Date(),
    due_date: new Date(),
    total_btw: 0,
    total_amount: 0,
    status: '',
    invoice_number: 0
  };

  customers: Customers[] = [];
  products: Products[] = [];
  invoiceItems: InvoiceItem[] = [];
  cars: Car[] = [];
  // Array om auto's op te slaan
  selectedCarId = 0;
  selectedCustomerId= 0;
  selectedCar: Car = { car_id: 0, customer_id: this.selectedCustomerId, plate_number: '', brand: '', model: '', year: 0, chasi_number: ''};
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
    this.apiService.getProducts().subscribe((data: Products[]) => {
      this.products = data;
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

    // Haal de auto's op van de geselecteerde klant
    this.apiService.getCarsByCustomerId(this.selectedCustomer.customer_id).subscribe(
      (data: Car[]) => {
        this.cars = data; // Sla de auto's op in de array
        if (this.cars.length > 0) {
          this.selectedCarId = this.cars[0].car_id;
          this.onCarChange();
        } else {
          this.invoice.car_id = null;
        }
      },
      error => {
        console.error('Fout bij ophalen van auto\'s:', error);
      }
    );
  }


  onCarChange() {
      this.selectedCar = this.cars.find(car => car.car_id === +this.selectedCarId) || {
        car_id: 0,
        customer_id: 0,
        plate_number: '',
        brand: '',
        model: '',
        year: 0,
        chasi_number: ''
      };
      this.invoice.car_id = this.selectedCarId;
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

  onProductChange(item: InvoiceItem) {
    // Forceer product_id vergelijking als nummers om inconsistentie te vermijden
    const selectedProduct = this.products.find(p => +p.product_id === +item.product_id);

    if (selectedProduct) {
      item.unit_price = selectedProduct.price;
      item.btw = selectedProduct.btw; // Pas de BTW aan indien nodig
      this.calculateTotals();
    } else {
      console.warn('Product niet gevonden voor product_id:', item.product_id);
    }
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
    // Bereken de totalen nogmaals voor de zekerheid
    this.calculateTotals();

    const invoiceToSend = {
      ...this.invoice,
      items: this.invoiceItems // Voeg de factuuritems toe aan de factuur
    };

    this.apiService.addInvoice(invoiceToSend).subscribe(
      response => {
        console.log(response);
        this.router.navigate(['/invoices'],);
      },
      error => {
        console.error('Er is een fout opgetreden:', error); // Fout afvangen
      }
    );
  }
}
