
describe('Invoices Component', () => {
  beforeEach(() => {
    // Bezoek de pagina met de factuurlijst
    cy.visit('/invoices'); // Zorg dat deze route correct is
  });

  it('should fetch and display invoices on init', () => {
    // Controleer of de tabel met facturen wordt weergegeven
    cy.get('.invoice-table').should('exist'); // Pas de class `.invoice-table` aan als nodig
    cy.get('.invoice-row').should('have.length.greaterThan', 0); // Zorg dat er facturen in de lijst staan
  });

  it('should filter invoices by number', () => {
    // Voer een factuurnummer in het zoekveld in
    cy.get('input[placeholder="Zoeken op factuurnummer"]').type('1001');

    // Controleer of alleen de juiste factuur wordt weergegeven
    cy.get('.invoice-row').should('have.length', 1); // Alleen één factuur
    cy.get('.invoice-row').contains('1001'); // Controleer of factuurnummer 1001 zichtbaar is
  });

  it('should filter invoices by customer ID', () => {
    // Voer een klant-ID in het zoekveld in
    cy.get('input[placeholder="Zoeken op klant-ID"]').type('102');

    // Controleer of alleen de juiste facturen worden weergegeven
    cy.get('.invoice-row').should('have.length', 1);
    cy.get('.invoice-row').contains('102'); // Controleer of klant-ID 102 zichtbaar is
  });

  it('should filter invoices by status', () => {
    // Selecteer de statusfilter
    cy.get('select[name="statusFilter"]').select('Paid');

    // Controleer of alleen facturen met de status 'Paid' worden weergegeven
    cy.get('.invoice-row').each(($row) => {
      cy.wrap($row).contains('Paid'); // Zorg dat elke rij de status 'Paid' bevat
    });
  });

  it('should filter invoices by date', () => {
    // Selecteer een datum
    const today = new Date().toISOString().split('T')[0]; // Haal alleen de datum op
    cy.get('input[name="dateFilter"]').type(today);

    // Controleer of de facturen correct worden gefilterd
    cy.get('.invoice-row').should('exist'); // Controleer of er rijen zijn (pas aan als nodig)
  });

  it('should navigate to invoice details when clicked', () => {
    // Klik op de eerste factuur in de lijst
    cy.get('.invoice-row').first().click();

    // Controleer of de details worden weergegeven
    cy.url().should('include', '/invoices/'); // Controleer of de URL naar de factuurdetails gaat
    cy.get('h1').contains('Factuur Details').should('be.visible'); // Controleer of de details zichtbaar zijn
  });
});
