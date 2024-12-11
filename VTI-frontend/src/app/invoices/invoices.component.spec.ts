import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InvoicesComponent } from './invoices.component';
import { ApiService } from '../api.service';
import { of } from 'rxjs';
//import { Router } from '@angular/router';

describe('InvoicesComponent', () => {
  let component: InvoicesComponent;
  let fixture: ComponentFixture<InvoicesComponent>;
  let apiService: ApiService;
 // let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        InvoicesComponent
      ],
      providers: [ApiService]
    })
      .compileComponents();

    fixture = TestBed.createComponent(InvoicesComponent);
    component = fixture.componentInstance;
    apiService = TestBed.inject(ApiService);
    //router = TestBed.inject(Router);
    fixture.detectChanges();
  });



  it('should fetch invoices on init', () => {
    const mockInvoices = [
      { invoice_id: 1, customer_id: 101, car_id: 2,invoice_date: new Date(), due_date: new Date(), total_btw: 11 , total_amount: 100, status: 'Paid', invoice_number: 1001 },
      { invoice_id: 2, customer_id: 102, car_id: 2, invoice_date: new Date(), due_date: new Date(), total_btw: 22 ,total_amount: 200, status: 'Unpaid', invoice_number: 1002 }
    ];

    spyOn(apiService, 'getInvoices').and.returnValue(of(mockInvoices));

    component.ngOnInit();
    fixture.detectChanges();


  });

  

  it('should filter invoices by Number', () => {
    component.invoices = [
      { invoice_id: 1, customer_id: 101, car_id: 2,invoice_date: new Date(), due_date: new Date(), total_btw: 11 , total_amount: 100, status: 'Paid', invoice_number: 1001 },
      { invoice_id: 2, customer_id: 102, car_id: 2, invoice_date: new Date(), due_date: new Date(), total_btw: 22 ,total_amount: 200, status: 'Unpaid', invoice_number: 1002 }
    ];

    component.searchNumber = '1001';
    component.applyFilters();


  });

  it('should filter invoices by customer ID', () => {
    component.invoices = [
      { invoice_id: 1, customer_id: 101, car_id: 2,invoice_date: new Date(), due_date: new Date(), total_btw: 11 , total_amount: 100, status: 'Paid', invoice_number: 1001 },
      { invoice_id: 2, customer_id: 102, car_id: 2, invoice_date: new Date(), due_date: new Date(), total_btw: 22 ,total_amount: 200, status: 'Unpaid', invoice_number: 1002 }
    ];

    component.searchCustomerId = '102';
    component.applyFilters();
    
  });

  it('should filter invoices by status', () => {
    component.invoices = [
      { invoice_id: 1, customer_id: 101, car_id: 2,invoice_date: new Date(), due_date: new Date(), total_btw: 11 , total_amount: 100, status: 'Paid', invoice_number: 1001 },
      { invoice_id: 2, customer_id: 102, car_id: 2, invoice_date: new Date(), due_date: new Date(), total_btw: 22 ,total_amount: 200, status: 'Unpaid', invoice_number: 1002 }
    ];

    component.statusFilter = 'Paid';
    component.applyFilters();
    
  });

  it('should filter invoices by date', () => {
    const today = new Date();
    const otherDate = new Date();
    otherDate.setDate(today.getDate() - 1);

    component.invoices = [
      { invoice_id: 1, customer_id: 101, car_id: 2,invoice_date: new Date(), due_date: new Date(), total_btw: 11 , total_amount: 100, status: 'Paid', invoice_number: 1001 },
      { invoice_id: 2, customer_id: 102, car_id: 2, invoice_date: new Date(), due_date: new Date(), total_btw: 22 ,total_amount: 200, status: 'Unpaid', invoice_number: 1002 }
    ];

    component.dateFilter = today.toISOString();
    component.applyFilters();
    
  });
});
