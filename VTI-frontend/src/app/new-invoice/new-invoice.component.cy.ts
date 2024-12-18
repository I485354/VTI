import { mount } from 'cypress/angular';
import { NewInvoiceComponent } from './new-invoice.component';
import { ApiService } from '../api.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Routes } from '@angular/router';

@Component({ template: '<p>Nieuwe factuur pagina</p>' })
class DummyComponent {}


const mockRoutes: Routes = [
  { path: 'invoices', component: DummyComponent }, // Voeg een mockroute toe
];
// Mock Data
const mockCustomers = [
  { customer_id: 1, name: 'Jan Jansen', company: 'Jansen BV', address: 'Straat 1', email: 'jan@jansen.nl', phone: '0612345678' },
  { customer_id: 2, name: 'Piet Pietersen', company: 'Pietersen NV', address: 'Laan 2', email: 'piet@pietersen.nl', phone: '0687654321' },
];

const mockCars = [
  { car_id: 1, customer_id: 1, plate_number: 'XX-123-YY', brand: 'Toyota', model: 'Corolla', year: 2020, chasi_number: 'ABC123' },
];

const mockProducts = [
  { product_id: 1, name: 'Olie verversen', description: 'olie', price: 100, quantity: 1, btw: 21 },
  { product_id: 2, name: 'Banden vervangen',description: '4 nieuwe banden', price: 75, quantity: 1, btw: 21 },
];

describe('NewInvoiceComponent', () => {
  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService-methoden
    apiServiceMock = {
      getCustomers: () => of(mockCustomers),
      getCarsByCustomerId: (id: number) => of(mockCars.filter(car => car.customer_id === id)),
      getProducts: () => of(mockProducts),
      addInvoice: (invoice) => of({ ...invoice, invoice_id: 123 }),
    };

    // Mount de component met mockdependencies
    mount(NewInvoiceComponent, {
      imports: [CommonModule, FormsModule, RouterTestingModule.withRoutes(mockRoutes)],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should initialize customers and products', () => {
    // Controleer klantopties
    cy.get('select#customerId').should('exist').and('contain', 'Jan Jansen').and('contain', 'Piet Pietersen');

    // Controleer producten
    cy.get('select').should('contain', 'Olie verversen').and('contain', 'Banden vervangen');
  });

  it('should load cars when a customer is selected', () => {
    // Selecteer een klant
    cy.get('select#customerId').select('1');
    cy.get('select#carId').should('contain', 'XX-123-YY');
  });

  it('should update product prices and calculate totals', () => {
    // Voeg een product toe
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0').select('1'); // Selecteer "Olie verversen"

    // Controleer de eenheidsprijs en BTW
    cy.get('#unitPrice0').should('have.value', '100');
    cy.get('#btw0').should('have.value', '21');

    // Controleer de totaalberekening
    cy.contains('Totaal (€):').parent().should('contain', '€121.00');
  });

  it('should calculate totals for multiple items', () => {
    // Voeg twee producten toe
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0').select('1'); // Product 1
    cy.get('#productSelect1').select('2'); // Product 2

    // Controleer totaalbedrag inclusief BTW
    cy.get('input#totalAmount').should('have.value', '211.75'); // 121 + 242
  });

  it('should submit the form succesful', () => {
    cy.window().then((win) => {
      cy.spy(win.console, 'log');
      cy.stub(win, 'open').as('routerNavigate');
    });

    // Vul klant en productvelden in
    cy.get('select#customerId').select('1');
    cy.get('select#carId').select('1');
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0').select('1');
    cy.get('#status').select('Betaald');

    cy.get('button[type="submit"]').should('not.be.disabled');
    cy.get('button[type="submit"]').click();

    cy.window().its('console.log').should('be.calledWithMatch', 'Invoice succesfull made');
    cy.get('@routerNavigate').should('not.be.calledWith', '/invoices');
  });
});
