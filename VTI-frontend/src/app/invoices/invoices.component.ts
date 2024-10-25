import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { Invoice } from '../model/invoices.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-invoices',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {
  invoices: Invoice[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    this.apiService.getInvoices().subscribe((data: Invoice[]) => {
      this.invoices = data;
    });
  }

  createNewInvoice() {
    this.router.navigate(['/new-invoice']).then(
      success => {
        console.log('Navigatie geslaagd:', success);
      },
      error => {
        console.error('Navigatie mislukt:', error);
      }
    );
  }
}
