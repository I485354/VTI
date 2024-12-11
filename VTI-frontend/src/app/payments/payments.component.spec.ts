import { mount } from 'cypress/angular';
import { PaymentsComponent } from './payments.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service'; // Mock of echte ApiService
import { of } from 'rxjs';

describe('PaymentsComponent', () => {
  beforeEach(() => {
    // Mock ApiService als nodig
    const mockApiService = {
      getPayments: () => of([
        { payment_id: 1, invoice_id: 101, amount: 100.00, date: '2024-12-01' },
        { payment_id: 2, invoice_id: 102, amount: 200.00, date: '2024-12-05' },
      ]),
    };

    // Mount de component
    mount(PaymentsComponent, {
      imports: [FormsModule, HttpClientTestingModule],
      providers: [{ provide: ApiService, useValue: mockApiService }], // Gebruik de mock of echte service
    });
  });

  it('should render the component correctly', () => {
    // Controleer dat de component is geladen
    cy.get('h1').should('contain', 'Payments'); // Controleer dat een titel aanwezig is
  });

});
