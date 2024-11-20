import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { Invoice } from '../model/invoices.model';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-invoices',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {
  invoices: Invoice[] = [];
  filteredInvoices: Invoice[] = []; // Array voor gefilterde facturen
  searchNumber = '';
  searchCustomerId = '';
  statusFilter = '';
  dateFilter: string | null = null; // Factuurdatum filter

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    this.apiService.getInvoices().subscribe((data: Invoice[]) => {
      this.invoices = data;
      this.filteredInvoices = data; // Standaard alle facturen weergeven
    });
  }
  updateStatus(invoice: Invoice) {
    const newStatus = invoice.status === 'Open' ? 'Betaald' : 'Open';

    this.apiService.updateInvoiceStatus(invoice.invoice_id, newStatus).subscribe(
      updatedInvoice => {
        invoice.status = updatedInvoice.status;
        console.log(`Factuurstatus bijgewerkt naar: ${invoice.status}`);
      },
      error => {
        console.error('Fout bij het bijwerken van de status:', error);
      }
    );
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

  // Functie om filters toe te passen op de factuurlijst
  applyFilters() {
    this.filteredInvoices = this.invoices.filter(invoice => {
      const matchesNummer = this.searchNumber ? invoice.invoice_number.toString().includes(this.searchNumber) : true;
      const matchesCustomerId = this.searchCustomerId ? invoice.customer_id.toString().includes(this.searchCustomerId) : true;
      const matchesStatus = this.statusFilter ? invoice.status === this.statusFilter : true;
      const matchesDate = this.dateFilter ?
        new Date(invoice.invoice_date).toLocaleDateString() === new Date(this.dateFilter).toLocaleDateString()
        : true;

      return matchesNummer && matchesCustomerId && matchesStatus && matchesDate;
    });
  }
}
