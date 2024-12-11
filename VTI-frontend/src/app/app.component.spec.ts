import { mount } from 'cypress/angular';
import { AppComponent } from './app.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('AppComponent', () => {
  beforeEach(() => {
    mount(AppComponent, {
      imports: [RouterTestingModule],
    });
  });

  it('should create the app', () => {
    // Controleer of het component correct is geladen
    cy.get('app-root').should('exist');
  });

  it(`should have the title 'VTI-frontend'`, () => {
    // Controleer of de titel 'VTI-frontend' in de component aanwezig is
    cy.window().its('app').then((app: any) => {
      expect(app.title).to.equal('VTI-frontend');
    });
  });

  it('should render title', () => {
    // Controleer of de titel correct wordt weergegeven in de HTML
    cy.contains('VTI-frontend').should('be.visible');
  });
});

