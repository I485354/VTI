import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from './model/customer.model';
import {Invoice} from './model/invoices.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080/api'; // API URL

  constructor(private http: HttpClient) { }


  getCustomers(): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.apiUrl}/customer`);
  }

  // API om facturen op te halen
  getInvoices(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`${this.apiUrl}/invoice`);
  }
  addInvoice(invoice: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>(`${this.apiUrl}/invoice`, invoice);
  }
  updateInvoiceStatus(invoiceId: number, status: string): Observable<Invoice> {
    return this.http.put<Invoice>(`${this.apiUrl}/invoice/${invoiceId}/status`, { status });
  }
  // API voor andere data zoals betalingen, offertes, etc.
}
