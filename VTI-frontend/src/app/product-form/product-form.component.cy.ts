import { mount } from 'cypress/angular';
import { ProductFormComponent } from './product-form.component';
import { RouterTestingModule } from '@angular/router/testing';
import { ApiService } from '../api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { of } from 'rxjs';
import { Component } from '@angular/core';
import { Routes } from '@angular/router';

@Component({ template: '<p>Nieuwe factuur pagina</p>' })
class DummyComponent {}


const mockRoutes: Routes = [
  { path: 'quotes', component: DummyComponent }, // Voeg een mockroute toe
];

describe('ProductFormComponent', () => {
  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock de API-service
    apiServiceMock = {
      addProduct: cy.stub().returns(of({ product_id: 1, name: 'Test Product', description: 'Beschrijving', price: 100, quantity: 15, btw: 21 })),
    };

    // Mount de component
    mount(ProductFormComponent, {
      imports: [CommonModule, FormsModule, RouterTestingModule.withRoutes(mockRoutes)],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });

  it('should render the form fields', () => {
    // Controleer of de inputvelden correct worden weergegeven
    cy.get('input#name').should('exist');
    cy.get('input#description').should('exist');
    cy.get('input#price').should('exist');
    cy.get('select#btw').should('exist');
    cy.get('input#quantity').should('exist');
  });

  it('should fill in the fields, submit, and log the correct data', () => {
    // Arrange: Vul de velden in
    cy.get('input#name').type('banden');
    cy.get('input#description').type('banden nieuw');
    cy.get('input#price').clear().type('100');
    cy.get('#btw').select('21');
    cy.get('input#quantity').clear().type('2');

    // Act: Klik op de opslaan-knop
    cy.window().then((win) => {
      // Mock de console.log om te controleren of de juiste gegevens worden gelogd
      const formSubmitSpy = cy.spy(win.console, 'log');
      cy.wrap(formSubmitSpy).as('formSubmitSpy'); // Maak een alias voor de spy
    });

    cy.get('button[type="submit"]').click();

    // Assert: Controleer of console.log correct is aangeroepen
    cy.get('@formSubmitSpy').should('be.calledWithMatch', 'Product toegevoegd:', {
      product_id: 0, // Standaardwaarde
      name: 'banden',
      description: 'banden nieuw',
      price: 100,
      quantity: 2,
      btw: 21,
    });
  });
});

