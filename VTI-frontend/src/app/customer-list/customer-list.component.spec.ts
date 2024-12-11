
describe('Customer List Component', () => {
  beforeEach(() => {
    // Bezoek de pagina waar de CustomerListComponent wordt geladen
    cy.visit('/customer-list'); // Zorg ervoor dat '/customer-list' de juiste route is
  });

  it('should render the customer list page', () => {
    // Controleer of de pagina geladen is
    cy.get('h1').contains('Klantenlijst').should('be.visible'); // Controleer of de titel 'Klantenlijst' zichtbaar is
  });
});
