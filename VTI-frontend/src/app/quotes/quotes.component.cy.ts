import { mount } from 'cypress/angular';
import { QuotesComponent } from './quotes.component';
import { Products } from '../model/products.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { ApiService } from '../api.service';

describe('QuotesComponent', () => {
  const mockProducts: Products[] = [
    { product_id: 1, name: 'Product A', description: 'Beschrijving A', price: 10,btw: 21, quantity: 1 },
    { product_id: 2, name: 'Product B', description: 'Beschrijving B', price: 20, btw: 21, quantity: 1 },
  ];

  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    apiServiceMock = {
      getProducts: () => of(mockProducts),
    };

    mount(QuotesComponent, {
      imports: [CommonModule, FormsModule, RouterTestingModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should render the quotes page with product list and quote summary', () => {
    // Controleer of de titel aanwezig is
    cy.get('h2').contains('Offertepagina').should('be.visible');

    // Controleer of de productenlijst geladen is
    cy.get('.products-list h3').contains('Beschikbare Producten').should('be.visible');
    cy.get('.products-list table tbody tr').should('have.length', mockProducts.length);

    // Controleer de kolommen in de productenlijst
    cy.get('.products-list table tbody tr').first().within(() => {
      cy.contains('Product A').should('be.visible');
      cy.contains('Beschrijving A').should('be.visible');
      cy.contains('€10.00').should('be.visible');
      cy.contains('Toevoegen aan offerte').should('be.visible');
    });

    // Controleer het offerte-overzicht
    cy.get('.quote-summary h3').contains('Offerte Overzicht').should('be.visible');
    cy.get('.quote-summary p').contains('Geen producten toegevoegd aan de offerte.').should('be.visible');
  });

  it('should add a product to the quote', () => {
    // Klik op "Toevoegen aan offerte" voor het eerste product
    cy.get('.products-list table tbody tr').first().contains('Toevoegen aan offerte').click();

    // Controleer of het product in het offerte-overzicht verschijnt
    cy.get('.quote-summary table tbody tr').should('have.length', 1);
    cy.get('.quote-summary table tbody tr').first().within(() => {
      cy.contains('Product A').should('be.visible');
      cy.contains('1').should('be.visible'); // Aantal
      cy.contains('€10.00').should('be.visible'); // Prijs
      cy.contains('€10.00').should('be.visible'); // Totaal
    });

    // Controleer het totaalbedrag
    cy.get('.quote-summary table tfoot tr').within(() => {
      cy.contains('Totaal').should('be.visible');
      cy.contains('€10.00').should('be.visible');
    });
  });

  it('should update the quantity when the same product is added again', () => {
    // Klik tweemaal op "Toevoegen aan offerte" voor het eerste product
    cy.get('.products-list table tbody tr').first().contains('Toevoegen aan offerte').click();
    cy.get('.products-list table tbody tr').first().contains('Toevoegen aan offerte').click();

    // Controleer of de hoeveelheid wordt bijgewerkt
    cy.get('.quote-summary table tbody tr').should('have.length', 1);
    cy.get('.quote-summary table tbody tr').first().within(() => {
      cy.contains('Product A').should('be.visible');
      cy.contains('2').should('be.visible'); // Aantal
      cy.contains('€10.00').should('be.visible'); // Prijs
      cy.contains('€20.00').should('be.visible'); // Totaal
    });

    // Controleer het totaalbedrag
    cy.get('.quote-summary table tfoot tr').within(() => {
      cy.contains('Totaal').should('be.visible');
      cy.contains('€20.00').should('be.visible');
    });
  });

  it('should calculate the total price correctly for multiple products', () => {
    // Voeg beide producten toe aan de offerte
    cy.get('.products-list table tbody tr').each(($row) => {
      cy.wrap($row).contains('Toevoegen aan offerte').click();
    });

    // Controleer of beide producten in het offerte-overzicht staan
    cy.get('.quote-summary table tbody tr').should('have.length', mockProducts.length);

    // Controleer het totaalbedrag
    cy.get('.quote-summary table tfoot tr').within(() => {
      cy.contains('Totaal').should('be.visible');
      cy.contains('€30.00').should('be.visible'); // €10 + €20
    });
  });
});
