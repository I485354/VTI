import { mount } from 'cypress/angular';
import { Routes } from '@angular/router';
import { InvoicesComponent } from './invoices.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { Invoice } from '../model/invoices.model';
import { Component } from '@angular/core';

@Component({ template: '<p>Nieuwe factuur pagina</p>' })
class DummyComponent {}

const dateToday = new Date();
const addDays = (date: Date, days: number): Date => {
  const result = new Date(date);
  result.setDate(result.getDate() + days);
  return result;
};
const mockRoutes: Routes = [
  { path: 'new-invoice', component: DummyComponent }, // Voeg een mockroute toe
];



const mockInvoices: Invoice[] = [
  {
    invoice_id: 1,
    invoice_number: 11,
    customer_id: 123,
    car_id: 1,
    invoice_date: dateToday,
    due_date: addDays(dateToday, 30),
    total_amount: 500.0,
    total_btw: 21,
    status: 'Open',
  },
  {
    invoice_id: 2,
    invoice_number: 12,
    customer_id: 456,
    car_id: 2,
    invoice_date: new Date(),
    due_date: new Date(30),
    total_amount: 750.0,
    total_btw: 57,
    status: 'Betaald',
  },
];

describe('InvoicesComponent', () => {
  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService-methoden
    apiServiceMock = {
      getInvoices: () => of(mockInvoices),
      updateInvoiceStatus: (invoiceId: number, updateStatus) => {
        const updatedInvoice = mockInvoices.find((inv) => inv.invoice_id === invoiceId);
        if (updatedInvoice) updatedInvoice.status = updateStatus.status;
        return of(updatedInvoice as Invoice); // Zorg dat dit type Invoice is
      },
    };

    // Mount de component met dependencies
    mount(InvoicesComponent, {
      imports: [CommonModule, FormsModule, RouterTestingModule.withRoutes(mockRoutes)],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should display a list of invoices', () => {
    const expectedDate = new Date().toLocaleDateString('nl-NL', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
    });
    // Controleer de tabel en aantal rijen
    cy.get('table.invoice-table').should('exist');
    cy.get('table tbody tr').should('have.length', 2);

    // Controleer inhoud van de eerste rij
    cy.get('table tbody tr').first().within(() => {
      cy.contains('11').should('be.visible');
      cy.contains('123').should('be.visible');
      cy.contains(expectedDate).should('be.visible');
      cy.contains('€500.00').should('be.visible');
      cy.contains('Open').should('be.visible');
    });
  });

  it('should filter invoices by invoice number', () => {
    // Typ een factuurnummer in het zoekveld
    cy.get('input[placeholder="Zoek op Factuur Nummer"]').type('12');
    cy.get('table tbody tr').should('have.length', 1);
    cy.contains('12').should('be.visible');
  });

  it('should filter invoices by customer ID', () => {
    cy.get('input[placeholder="Zoek op Klant ID"]').type('123');
    cy.get('table tbody tr').should('have.length', 1);
    cy.contains('123').should('be.visible');
  });

  it('should filter invoices by status', () => {
    // Selecteer "Betaald" in de status dropdown
    cy.get('select').select('Betaald');
    cy.get('table tbody tr').should('have.length', 1);
    cy.contains('Betaald').should('be.visible');
  });

  it('should display "Geen facturen gevonden" when no results match filters', () => {
    cy.get('input[placeholder="Zoek op Factuur Nummer"]').type('INVALID');
    cy.contains('Geen facturen gevonden').should('be.visible');
    cy.get('table').should('not.exist');
  });

  it('should update invoice status when the action button is clicked', () => {

    cy.get('table tbody tr').first().within(() => {
      cy.contains('Markeer als Betaald').click();
      cy.contains('Betaald').should('be.visible');
    });

    cy.get('table tbody tr').first().within(() => {
      cy.contains('Markeer als Niet Betaald').click();
      cy.contains('Open').should('be.visible');
    });
  });

  it('should navigate to create new invoice page', () => {
    cy.window().then((win) => cy.spy(win.console, 'log'));

    cy.get('.btn-add').click();

    cy.window().its('console.log').should('be.calledWith', 'Navigatie geslaagd:', true);
  });
});
