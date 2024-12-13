import { mount } from 'cypress/angular';
import { CustomerListComponent } from './customer-list.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { of } from 'rxjs';

describe('CustomerListComponent', () => {
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
   /* {
      customer_id: 3,
      name: 'Alice Brown',
      email: 'alice.brown@example.com',
      company: 'Brown LLC',
      phone: '555555555',
      address: 'Third Boulevard 33',
    }*/
  ];

  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService
    apiServiceMock = {
      getCustomers: () => of(mockCustomers),
      addCustomer: (customer) => of({ ...customer, customer_id: mockCustomers.length + 1 }),
    };

    mount(CustomerListComponent, {
      imports: [RouterTestingModule, HttpClientModule, FormsModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should render the customer list and display customers', () => {
    // Check if the title is displayed
    cy.get('h2.title').should('contain', 'Klantenlijst');

    // Verify customers are displayed in the table
    cy.get('table.customer-table tbody tr').should('have.length', 2);
    cy.get('table.customer-table').contains('td', 'John Doe').should('be.visible');
    cy.get('table.customer-table').contains('td', 'Jane Smith').should('be.visible');
  });

  it('should validate the customer form', () => {

    cy.get('button.add-button').click();

    cy.get('button.save-button').click();

    // Check for error messages
    cy.get('.error-message').should('contain', 'Name is required'); // Example error
  });

  it('should add a new customer', () => {
    // Click the "Add Customer" button
    cy.get('button.add-button').click();

    // Fill in the customer form
    cy.get('input#name').type('Alice Brown');
    cy.get('input#email').type('alice.brown@example.com');
    cy.get('input#company').type('Brown LLC');
    cy.get('input#phone').type('555555555');
    cy.get('input#address').type('Third Boulevard 33');

    // Click the "Save" button
    cy.get('button.save-button').click();

    // Verify the new customer is added to the table
    cy.get('table.customer-table tbody tr').should('have.length', 3);
    cy.get('table.customer-table').contains('td', 'Alice Brown').should('be.visible');
  });
  
  it('should see Bewerkpagina', () => {
    cy.get('Bewerk').click();
    
  });
  
});

