



describe('Sidebar Component', () => {
  beforeEach(() => {
    // Bezoek de pagina waar de Sidebar wordt geladen
    cy.visit('/');
  });

  it('should display all sidebar links', () => {
    // Controleer of de sidebar aanwezig is
    cy.get('.sidebar').should('exist');

    // Controleer of bepaalde links in de sidebar aanwezig zijn
    const sidebarLinks = ['Dashboard', 'Facturen', 'Offertes', 'Klanten', 'Betalingen', 'Instellingen'];
    sidebarLinks.forEach((link) => {
      cy.contains(link).should('exist');
    });
  });
});
