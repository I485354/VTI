import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../environments/environment";
import { CustomerInfo } from "./model/CustomerInfo.model"
import { Invoice } from "./model/Invoice.model"

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = environment.apiUrl;


  constructor(private http: HttpClient) {
  }
  getCustomer(id: number): Observable<CustomerInfo> {
    console.log('API Call:', `${this.apiUrl}/customer/info/${id}`);
    return this.http.get<CustomerInfo>(`${this.apiUrl}/customer/info/${id}`);
  }

  getInvoices(id: number): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(`${this.apiUrl}/customer/invoice/${id}`);
  }
}
