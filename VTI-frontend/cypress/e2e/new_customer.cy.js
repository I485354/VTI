describe('New customer', () => {
  beforeEach(() => {

    cy.visit('/customer-list',  {failOnStatusCode: false}); // Pas aan naar jouw pagina-URL
  });

  it('Should open the add customer form when clicking the "Voeg Klant Toe" button', () => {
    // Zoek en klik op de "Voeg Klant Toe" knop
    cy.contains('Klant Toevoegen').click();

    // Controleer of het formulier zichtbaar is
    cy.contains('Nieuwe Klant Toevoegen').should('be.visible');
    cy.get('input#name').should('exist');
    cy.get('input#email').should('exist');
    cy.get('input#company').should('exist');
    cy.get('input#phone').should('exist');
    cy.get('input#address').should('exist');
  });

  it('Should fill out the form and save a new customer', () => {
    // Klik op de "Voeg Klant Toe" knop
    cy.contains('Klant Toevoegen').click();

    // Vul de velden in
    cy.get('input#name').type('John Doe');
    cy.get('input#email').type('john.doe@example.com');
    cy.get('input#company').type('Doe Enterprises');
    cy.get('input#phone').type('0612345678');
    cy.get('input#address').type('123 Main Street');

    // Klik op de "Opslaan" knop
    cy.contains('Opslaan').click();

    cy.wait(5000);

    // Controleer of de klant wordt weergegeven in de klantenlijst
    cy.contains('John Doe').should('be.visible');
  });

  it('Should cancel adding a new customer', () => {
    // Klik op de "Voeg Klant Toe" knop
    cy.contains('Klant Toevoegen').click();

    // Vul een veld in
    cy.get('input#name').type('Test Cancel');

    // Klik op de "Annuleren" knop
    cy.contains('Annuleren').click();

    // Controleer of het formulier niet meer zichtbaar is
    cy.contains('Nieuwe Klant Toevoegen').should('not.exist');
  });
});
