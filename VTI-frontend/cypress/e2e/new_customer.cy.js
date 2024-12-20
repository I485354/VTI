describe('New customer', () => {
  beforeEach(() => {

    cy.visit('/customer-list',  {failOnStatusCode: false});
    cy.wait(1000);
  });

  it('Should open the add customer form when clicking the "Voeg Klant Toe" button', () => {

    cy.contains('Klant Toevoegen').click();


    cy.contains('Nieuwe Klant Toevoegen').should('be.visible');
    cy.get('input#name').should('exist');
    cy.get('input#email').should('exist');
    cy.get('input#company').should('exist');
    cy.get('input#phone').should('exist');
    cy.get('input#address').should('exist');
  });

  it('Should fill out the form and save a new customer', () => {

    cy.contains('Klant Toevoegen').click();


    cy.get('input#name').type('John Doe');
    cy.get('input#email').type('john.doe@example.com');
    cy.get('input#company').type('Doe Enterprises');
    cy.get('input#phone').type('0612345678');
    cy.get('input#address').type('123 Main Street');


    cy.contains('Opslaan').click();

    cy.wait(5000);


    cy.contains('John Doe').should('be.visible');
  });

  it('Should cancel adding a new customer', () => {

    cy.contains('Klant Toevoegen').click();


    cy.get('input#name').type('Test Cancel');


    cy.contains('Annuleren').click();


    cy.contains('Nieuwe Klant Toevoegen').should('not.exist');
  });
});
