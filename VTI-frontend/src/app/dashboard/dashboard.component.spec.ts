import { mount } from 'cypress/angular';
import { DashboardComponent } from './dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

describe('DashboardComponent', () => {
  beforeEach(() => {
    // Mount de component
    mount(DashboardComponent, {
      imports: [HttpClientModule, FormsModule, CommonModule],
    });
  });

  it('should create', () => {
    cy.visit('/');
    cy.get('app-dashboard').should('exist');
  });

  it('should display the current year in the dropdown', () => {
    // Controleer of het huidige jaar in de dropdown staat
    const currentYear = new Date().getFullYear();
    cy.get('select').should('contain', currentYear.toString());
  });

  it('should load data when a year is selected', () => {
    // Simuleer het selecteren van een jaar
    cy.intercept('GET', '**/invoice/revenue*', { fixture: 'revenue.json' }).as('getRevenue');
    cy.get('select').select('2024');

    // Wacht op de mock-API en controleer of data is geladen
    cy.wait('@getRevenue');
    cy.get('table').should('exist');
    cy.get('table tbody tr').should('have.length.greaterThan', 0);
  });

  it('should display a message if no data is available', () => {
    // Simuleer een lege dataset
    cy.intercept('GET', '**/invoice/revenue*', { body: [] }).as('getEmptyRevenue');
    cy.get('select').select('2025');

    // Controleer of de juiste melding wordt weergegeven
    cy.wait('@getEmptyRevenue');
    cy.contains('Geen kwartaalgegevens beschikbaar voor 2025').should('be.visible');
  });
});
