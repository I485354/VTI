
describe('API Service Integration', () => {
  it('should call the API and handle the response', () => {
    // Mock de API-respons met Cypress intercept
    cy.intercept('GET', '/api/some-endpoint', {
      statusCode: 200,
      body: { message: 'Success', data: [1, 2, 3] }, // Mock data
    }).as('getApiData');

    // Bezoek de pagina waar de API wordt aangeroepen
    cy.visit('/');

    // Wacht tot de API-aanroep is gedaan
    cy.wait('@getApiData').its('response.statusCode').should('eq', 200);

    // Controleer of de data correct wordt weergegeven in de UI
    cy.contains('Success').should('be.visible');
  });
});
