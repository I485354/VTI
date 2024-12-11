import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NewInvoiceComponent } from './new-invoice.component';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // Importeer HttpClientTestingModule
import { ApiService } from '../api.service';

describe('NewInvoiceComponent', () => {
  let component: NewInvoiceComponent;
  let fixture: ComponentFixture<NewInvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, NewInvoiceComponent],  // Voeg HttpClientTestingModule toe
      providers: [ApiService]  // Zorg dat ApiService beschikbaar is
    }).compileComponents();
  });
   
  beforeEach(() => {
    fixture = TestBed.createComponent(NewInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); 
  });

 
});
