import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from './model/customer.model';
import { Invoice } from './model/invoices.model';
import { Products } from './model/products.model';
import { Car } from './model/car.model';
import {Revenue} from './model/Revenue.model';
import { UpdateInvoiceStatus } from './model/UpdateInvoiceStatus.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080/api'; // API URL

  constructor(private http: HttpClient) { }


  getCustomers(): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.apiUrl}/customer`);
  }
  getInvoices(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`${this.apiUrl}/invoice`);
  }
  addInvoice(invoice: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>(`${this.apiUrl}/invoice/create_invoice`, invoice);
  }
  updateInvoiceStatus(invoiceId: number, updateStatus: UpdateInvoiceStatus): Observable<Invoice> {
    return this.http.put<Invoice>(`${this.apiUrl}/invoice/${invoiceId}/status`, updateStatus);
  }

  addCustomer(customer: Customers): Observable<Customers> {
    return this.http.post<Customers>(`${this.apiUrl}/customer`, customer);
  }
  deleteCustomer(customerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/customer/${customerId}`);
  }

  updateCustomer(customer: Customers): Observable<Customers> {
    return this.http.put<Customers>(`${this.apiUrl}/customer/${customer.customer_id}`, customer);
  }
  getProducts(): Observable<Products[]> {
  return this.http.get<Products[]>(`${this.apiUrl}/product`);
}
  addProduct(product: Products): Observable<Products> {
    return this.http.post<Products>(`${this.apiUrl}/product`, product );
  }
  getCarsByCustomerId(customerId: number): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.apiUrl}/car/${customerId}`);
  }
  getOpenInvoicesCount(){
    return this.http.get<number>(`${this.apiUrl}/invoice/open-invoices`);
  }
  getRevenue(year: number){
    return this.http.get<Revenue[]>(`${this.apiUrl}/invoice/revenue?year=${year}`);
  }
  // API voor andere data zoals betalingen, offertes, etc.
}
