import { mount } from 'cypress/angular';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms'; // Voeg FormsModule toe als de login-formulier NgModel gebruikt

describe('LoginComponent', () => {
  beforeEach(() => {
    mount(LoginComponent, {
      imports: [FormsModule], // Voeg FormsModule toe voor formulierfunctionaliteit
    });
  });

  it('should render the login form', () => {
    // Controleer of het login-formulier zichtbaar is
    cy.get('form').should('exist');
    cy.get('input[name="username"]').should('exist');
    cy.get('input[name="password"]').should('exist');
    cy.get('button[type="submit"]').contains('Login').should('exist');
  });
});
