import { mount } from 'cypress/angular';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { ApiService } from './api.service';

describe('API Service Integration', () => {
  beforeEach(() => {
    mount(ApiService, {
      imports: [RouterTestingModule, HttpClientTestingModule, FormsModule],
    });
  });

    // Bezoek de pagina waar de API wordt aangeroepen
    cy.visit('/');

    // Wacht tot de API-aanroep is gedaan
    cy.wait('@getApiData').its('response.statusCode').should('eq', 200);

    // Controleer of de data correct wordt weergegeven in de UI
    cy.contains('Success').should('be.visible');
  });
