describe('Update customer', () => {
  beforeEach(() => {
    cy.visit('/customer-list',  {failOnStatusCode: false});
    cy.wait(1000);
  });

  it('Should open the update from with customer info filled in', () => {
    // Zoek en klik op de "Voeg Klant Toe" knop
    cy.contains('Bewerken').click();

    // Controleer of het formulier zichtbaar is
    cy.contains('Klant Bewerken').should('be.visible');
    cy.get('input#name').should('have.value', 'John Doe');
    cy.get('input#email').should('have.value', 'john.doe@example.com');
    cy.get('input#company').should('have.value', 'Doe Enterprises');
    cy.get('input#phone').should('have.value', '0612345678');
    cy.get('input#address').should('have.value', 'Nieuwe straat 1');
  });

  it('Should fill out the form and save a new customer', () => {
    // Klik op de "Voeg Klant Toe" knop
    cy.contains('Bewerken').click();

    // Vul de velden in
    cy.get('input#name').clear().type('John Doe'); // Maak het veld leeg en typ nieuwe waarde
    cy.get('input#email').clear().type('john.doe@example.com');
    cy.get('input#company').clear().type('Doe Enterprises');
    cy.get('input#phone').clear().type('0612345678');
    cy.get('input#address').clear().type('Nieuwe straat 1');

    // Klik op de "Opslaan" knop
    cy.contains('Opslaan').click();

    cy.wait(5000);

    // Controleer of de klant wordt weergegeven in de klantenlijst
    cy.contains('John Doe').should('be.visible');
  });

  it('Should cancel adding a new customer', () => {
    // Klik op de "Voeg Klant Toe" knop
    cy.contains('Bewerken').click();

    // Vul een veld in
    cy.get('input#name').type('Test Cancel');

    // Klik op de "Annuleren" knop
    cy.contains('Annuleren').click();

    // Controleer of het formulier niet meer zichtbaar is
    cy.contains('Nieuwe Klant Toevoegen').should('not.exist');
  });
});
