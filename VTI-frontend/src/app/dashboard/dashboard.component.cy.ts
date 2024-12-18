import { mount } from 'cypress/angular';
import { DashboardComponent } from './dashboard.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { of } from 'rxjs';
import { Revenue } from '../model/Revenue.model';

// Mock data
const mockQuarterlyRevenue: Revenue[] = [
  { year: 2024, quarter: 1, total_amount: 10000, invoice_count: 10 },
  { year: 2024, quarter: 2, total_amount: 15000, invoice_count: 15 },
  { year: 2024, quarter: 3, total_amount: 20000, invoice_count: 20 },
  { year: 2024, quarter: 4, total_amount: 25000, invoice_count: 25 },
];

describe('DashboardComponent (Unit Test)', () => {
  let apiServiceMock: Partial<ApiService>;

  beforeEach(() => {
    // Mock ApiService
    apiServiceMock = {
      getRevenue: () => of(mockQuarterlyRevenue),
    };

    mount(DashboardComponent, {
      imports: [CommonModule, FormsModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });
  });
  
  it('should initialize years correctly', () => {
    const currentYear = new Date().getFullYear();
    cy.get('select').should('contain', currentYear.toString());
    cy.get('select').should('contain', (currentYear - 9).toString());
  });

  it('should load and display mock quarterly revenue data', () => {
    // Controleer of data in de tabel wordt geladen
    cy.get('table').should('exist');
    cy.get('table tbody tr').should('have.length', 4);
    cy.get('table tbody').contains('td', '€ 10,000.00').should('be.visible'); // Q1
    cy.get('table tbody').contains('td', '€ 15,000.00').should('be.visible'); // Q2
    cy.get('table tbody').contains('td', '€ 20,000.00').should('be.visible'); // Q3
    cy.get('table tbody').contains('td', '€ 25,000.00').should('be.visible'); // Q4
  });

  it('should display a message when no data is available', () => {
    // Mock lege dataset
    apiServiceMock.getRevenue = () => of([]);
    mount(DashboardComponent, {
      imports: [CommonModule, FormsModule],
      providers: [{ provide: ApiService, useValue: apiServiceMock }],
    });

    cy.get('select').select('2023');
    cy.contains('Geen kwartaalgegevens beschikbaar').should('be.visible');
  });
});

