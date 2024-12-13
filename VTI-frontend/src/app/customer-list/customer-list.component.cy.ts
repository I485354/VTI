import { mount } from 'cypress/angular';
import { CustomerListComponent } from './customer-list.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from '../api.service';
import { of } from 'rxjs';

describe('Customer List Component', () => {
  const mockCustomers = [
    {
      customer_id: 1,
      name: 'John Doe',
      email: 'john.doe@example.com',
      company: 'Doe Enterprises',
      phone: '1234567890',
      address: '123 Main Street',
    },
    {
      customer_id: 2,
      name: 'Jane Smith',
      email: 'jane.smith@example.com',
      company: 'Smith Co.',
      phone: '9876543210',
      address: '456 Side Avenue',
    },
  ];

  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService
    apiServiceMock = {
      getCustomers: () => of(mockCustomers), // Gebruik `of` van RxJS om een Observable te mocken
      addCustomer: (customer) => of({ ...customer, customer_id: mockCustomers.length + 1 }),
      updateCustomer: (customer) => of(customer),
      //deleteCustomer: (customerId) => of(),
    };

    // Mount de component
    mount(CustomerListComponent, {
      imports: [RouterTestingModule, HttpClientModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should render the customer list page and display customers', () => {
    // Controleer of de titel zichtbaar is
    cy.get('h2.title').should('contain', 'Klantenlijst');

    // Controleer of de klanten in de tabel zichtbaar zijn
    cy.get('table.customer-table tbody tr').should('have.length', 2);
    cy.get('table.customer-table').contains('td', 'John Doe').should('be.visible');
    cy.get('table.customer-table').contains('td', 'Jane Smith').should('be.visible');
  });

  it('should add a new customer', () => {
    // Klik op "Klant Toevoegen" knop
    cy.get('button.add-button').click();


    cy.get('input#name').type('Alice Brown');
    cy.get('input#email').type('alice.brown@example.com');
    cy.get('input#company').type('Brown LLC');
    cy.get('input#phone').type('555555555');
    cy.get('input#address').type('Third Boulevard 33');

    // Klik op "Opslaan"
    cy.get('button.save-button').click();

    // Controleer of de klant correct is toegevoegd
    cy.get('table.customer-table').contains('td', 'Alice Brown').should('be.visible');
  });
});
