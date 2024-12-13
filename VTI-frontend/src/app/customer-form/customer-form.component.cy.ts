import { mount } from 'cypress/angular';
import { CustomerFormComponent } from './customer-form.component';

describe('CustomerFormComponent', () => {
  beforeEach(() => {
    mount(CustomerFormComponent);
  });

  it('should render the form fields', () => {
    // Controleer of de inputvelden correct worden weergegeven
    cy.get('input#name').should('exist');
    cy.get('input#email').should('exist');
    cy.get('input#company').should('exist');
    cy.get('input#phone').should('exist');
    cy.get('input#address').should('exist');
  });

  it('should submit the form with correct data', () => {
    // Vul de formuliergegevens in
    cy.get('input#name').type('John Doe');
    cy.get('input#email').type('john.doe@example.com');
    cy.get('input#company').type('Doe Enterprises');
    cy.get('input#phone').type('123456789');
    cy.get('input#address').type('123 Main Street');

    // Mock de submit-functie van het component
    cy.window().then((win) => {
      const formSubmitSpy = cy.spy(win.console, 'log');
      cy.wrap(formSubmitSpy).as('formSubmitSpy');
    });

    // Klik op de opslaan-knop
    cy.get('button.save-button').click();
    cy.contains('Changes saved').should('be.visible');

    // Wacht tot het bericht verdwijnt
    cy.wait(3000);
    cy.contains('Changes saved').should('not.exist');

    // Controleer of de juiste gegevens zijn gelogd
    cy.get('@formSubmitSpy').should('be.calledWithMatch', 'Changes saved', {
      name: 'John Doe',
      email: 'john.doe@example.com',
      company: 'Doe Enterprises',
      phone: '123456789',
      address: '123 Main Street',
    });
  });

  it('should allow cancelling the form', () => {
    // Klik op de annuleren-knop
    cy.get('button.cancel-button').click();

    // Controleer of het formulier wordt gesloten of geannuleerd
    cy.get('app-customer-form').should('not.exist');
  });
});
