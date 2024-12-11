
describe('Quotes Component', () => {
  beforeEach(() => {
    // Bezoek de pagina waar de QuotesComponent wordt geladen
    cy.visit('/quotes'); // Zorg ervoor dat '/quotes' de juiste route is
  });

  it('should render the quotes page', () => {
    // Controleer of de pagina geladen is
    cy.get('h1').contains('Offertes').should('be.visible'); // Controleer of de titel 'Offertes' aanwezig is
  });

});
