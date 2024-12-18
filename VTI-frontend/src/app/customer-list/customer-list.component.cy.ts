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
  ];

  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService volledig
    apiServiceMock = {
      getCustomers: () => of(mockCustomers),
      addCustomer: (customer) => {
        // Simuleer het toevoegen van een klant
        const newCustomer = { ...customer, customer_id: mockCustomers.length + 1 };
        mockCustomers.push(newCustomer);
        return of(newCustomer);
      }
    };

    // Mount de component met dependencies
    mount(CustomerListComponent, {
      imports: [RouterTestingModule, HttpClientModule, FormsModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should render the customer list and display customers', () => {
    cy.get('h2.title').should('contain', 'Klantenlijst');
    cy.get('table.customer-table tbody tr').should('have.length', 2);
    cy.get('table.customer-table').contains('td', 'John Doe').should('be.visible');
    cy.get('table.customer-table').contains('td', 'Jane Smith').should('be.visible');
  });


  it('should add a new customer', () => {
    cy.get('button.add-button').click();

    cy.log('Vul formulier correct in');
    cy.get('input#name').type('Alice Brown');
    cy.get('input#email').type('alice.brown@example.com');
    cy.get('input#company').type('Brown LLC');
    cy.get('input#phone').type('555555555');
    cy.get('input#address').type('Third Boulevard 33');

    cy.log('Klik op de Save-knop');
    cy.get('button.save-button').click();

    cy.log('Controleer dat een nieuwe rij wordt toegevoegd');
    cy.get('table.customer-table tbody tr').should('have.length', 3);
    cy.get('table.customer-table').contains('td', 'Alice Brown').should('be.visible');
  });

  it('should see Bewerkpagina', () => {
    cy.log('Klik op de Edit-knop van de eerste klant');
    cy.get('table.customer-table tbody tr')
      .first()
      .find('button.edit-button')
      .click();

    cy.log('Controleer of het formulier gevuld is met klantdata');
    cy.get('input#name').should('have.value', 'John Doe');
    cy.get('h2.form-title').should('contain', 'Klant Bewerken');
  });

  it('should display errors when required fields are empty', () => {
    cy.get('button.add-button').click();

    cy.log('Laat alle velden leeg en klik op Save');
    cy.get('button.save-button').click({force: true});

    cy.get('.error-message').should('contain', 'Veld is verplicht');
  });
});

