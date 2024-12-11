
describe('Settings Component', () => {
  beforeEach(() => {
    // Bezoek de pagina waar de SettingsComponent wordt geladen
    cy.visit('/settings'); // Zorg ervoor dat '/settings' de route is waar de SettingsComponent beschikbaar is
  });

  it('should render the settings page', () => {
    // Controleer of de pagina geladen is
    cy.get('h1').contains('Instellingen').should('be.visible'); // Controleer of de titel 'Instellingen' aanwezig is
  });
 

});
