import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';
import { Customers } from '../model/customer.model';

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})

export class CustomerListComponent implements OnInit {

  customers: Customers[] = [];


  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getCustomers().subscribe((data: Customers[]) => {
      this.customers = data;
    });
  }
}
