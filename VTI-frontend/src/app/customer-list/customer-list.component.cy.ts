import { mount } from 'cypress/angular';
import { CustomerListComponent } from './customer-list.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http'; // Voeg HttpClientModule toe

describe('Customer List Component', () => {
  beforeEach(() => {
    mount(CustomerListComponent, {
      imports: [RouterTestingModule, HttpClientModule], // Voeg HttpClientModule toe aan imports
    });
  });

  it('should render the customer list page', () => {
    // Controleer of de pagina geladen is
    cy.get('h1').contains('Klantenlijst').should('be.visible'); // Controleer of de titel 'Klantenlijst' zichtbaar is
  });
});

