import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from './model/customer.model';
import { Invoice } from './model/invoices.model';
import { Products } from './model/products.model';

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
    return this.http.post<Invoice>(`${this.apiUrl}/invoice`, invoice);
  }
  updateInvoiceStatus(invoiceId: number, status: string): Observable<Invoice> {
    return this.http.put<Invoice>(`${this.apiUrl}/invoice/${invoiceId}/status`, { status });
  }
  addCustomer(customer: Customers): Observable<Customers> {
    return this.http.post<Customers>(`${this.apiUrl}/customer`, customer);
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
  // API voor andere data zoals betalingen, offertes, etc.
} 
