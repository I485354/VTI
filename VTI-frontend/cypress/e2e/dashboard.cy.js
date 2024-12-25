describe('Dashboard Tests', () => {

  beforeEach(() => {
    cy.visit('/login')
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');
    cy.get('button[type="submit"]').click();
    cy.visit('/dashboard', { failOnStatusCode: false });
    // Eventueel een korte pauze, of liever iets als cy.wait('@someAlias') als je met intercept werkt.
    cy.wait(1000);
  });

  it('Should load the dashboard page', () => {
    // Hoofd-titel
    cy.contains('Dashboard').should('be.visible');

    // Jaar-dropdown
    cy.get('select').should('be.visible');

    // Default: als er nog geen data is, zou er "Geen kwartaalgegevens beschikbaar" kunnen staan.
    // Pas deze tekst aan als je dat wilt/moet.
    cy.contains('Kwartaalcijfers voor').should('be.visible');
  });

  it('Should display the correct year in the dropdown', () => {
    // Check de default year-select. Huidig jaar is 2024 (voorbeeld).
    cy.get('select').should('have.value', new Date().getFullYear().toString());
  });

  it('Should display the correct table structure', () => {
    // Als er data is, moet er een <table> staan
    // De test hieronder faalt als er helemaal geen data is, dus kijk of 'hasData' echt true is
    cy.get('table').should('exist');

    // Check de kolomtitels
    cy.get('table thead').within(() => {
      cy.contains('Kwartaal').should('be.visible');
      cy.contains('Omzet (€)').should('be.visible');
    });

    // We verwachten precies 4 kwartalen, dus 4 table-rows:
    cy.get('table tbody tr').should('have.length', 4);
  });

  it('Should display correct revenue data for all quarters', () => {
    // Wacht tot de tabel gevuld is (controleer op aantal rijen).
    cy.get('table tbody tr').should('have.length', 4);

    // Loop door alle table rows heen
    cy.get('table tbody tr').each(($row, index) => {
      // Kwartaalnummer (1 t/m 4) checken
      cy.wrap($row)
        .find('td')
        .eq(0)
        .should('contain', index + 1);

      // Omzet‐veld checken.
      // Bijv. of het begint met "€", gevolgd door een numerieke waarde.
      // Hieronder een simpele regex-check als voorbeeld:
      cy.wrap($row)
        .find('td')
        .eq(1)
        .invoke('text')
        .then((text) => {
          const trimmed = text.trim();
          // Voorbeeld van regex die "€ 4.223,75" of "€ 0,00" of "€ 123" etc. toelaat
          // (Let op de regionale notatie: punt voor duizendtallen en komma voor decimalen.)
          const currencyRegex = /^€\s?\d{1,3}(,\d{3})*(\.\d{2})?$/;
          expect(trimmed).to.match(
            currencyRegex,
            `Omzet-veld moet een valuta-indicatie zijn, maar was: "${trimmed}"`
          );
        });
    });
  });

  it('Should show "No data available" message if no data is available for the year', () => {
    // Selecteer een jaar waarvan we (vermoedelijk) weten dat er geen data is
    cy.get('select').select('2020');

    // Dan moet de tabel verdwijnen...
    cy.get('table').should('not.exist');

    // ... en de "geen data" melding komen
    cy.contains('Geen kwartaalgegevens beschikbaar voor 2020.').should('be.visible');
  });
});
