import { mount } from 'cypress/angular';
import { NewInvoiceComponent } from './new-invoice.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service'; // Mock of echte ApiService
import { of } from 'rxjs';

describe('NewInvoiceComponent', () => {
  beforeEach(() => {
    // Mock ApiService als nodig
    const mockApiService = {
      getCustomers: () => of([{ customer_id: 1, name: 'John Doe', email: 'john.doe@example.com' }]),
      getProducts: () => of([{ product_id: 1, name: 'Product A', price: 100, btw: 21 }]),
      getCarsByCustomerId: () => of([{ car_id: 1, plate_number: 'ABC123', brand: 'Toyota' }]),
      addInvoice: () => of({ message: 'Invoice added successfully!' }),
    };

    // Mount de component
    mount(NewInvoiceComponent, {
      imports: [FormsModule, HttpClientTestingModule],
      providers: [{ provide: ApiService, useValue: mockApiService }], // Gebruik de mock of echte service
    });
  });

  it('should render the form correctly', () => {
    // Controleer dat het formulier geladen is
    cy.get('form').should('exist');

    // Controleer velden voor klantselectie en producten
    cy.get('select[name="customerId"]').should('exist');
    cy.get('button').contains('Voeg Item Toe').should('exist');
  });

 
});
