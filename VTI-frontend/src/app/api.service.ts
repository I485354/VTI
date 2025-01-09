import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from './model/customer.model';
import { Invoice } from './model/invoices.model';
import { Products } from './model/products.model';
import { Car } from './model/car.model';
import {Revenue} from './model/Revenue.model';
import { UpdateInvoiceStatus } from './model/UpdateInvoiceStatus.model';
import { createCar } from './model/createCar.model';
import { User } from './model/user.model';
import { UserLogin } from './model/userLogin.model';
import { AuthResponse } from './model/authResponse.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = environment.apiUrl; 
  

  constructor(private http: HttpClient) { }


  getCustomers(): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.apiUrl}/customer`);
  }
  getCustomerById(id: number): Observable<Customers> {
    return this.http.get<Customers>(`${this.apiUrl}/customer/customerById/${id}`);
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

  createCar(createcar: createCar): Observable<Car> {
    return this.http.post<Car>(`${this.apiUrl}/car`, createcar);
  }

  login(userLogin: UserLogin): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/user/login`, userLogin);
  }
  register(userDetails: UserLogin): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/user/register`, userDetails);
  }
}
