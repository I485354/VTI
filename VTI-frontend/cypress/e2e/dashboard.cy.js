describe('Dashboard Tests', () => {
  beforeEach(() => {
    cy.visit('/dashboard',  {failOnStatusCode: false});
    cy.wait(1000);
  });

  it('Should load the dashboard page', () => {
    cy.contains('Dashboard').should('be.visible');

    cy.get('select').should('be.visible');

    cy.contains('Geen kwartaalgegevens beschikbaar').should('be.visible');
  });

  it('Should display the correct year in the dropdown', () => {
    cy.get('select').should('have.value', '2024');
  });

  it('Should display the correct table structure', () => {

    cy.get('table').should('exist');


    cy.get('table thead').within(() => {
      cy.contains('Kwartaal').should('be.visible');
      cy.contains('Omzet (€)').should('be.visible');
    });

    cy.get('table tbody tr').should('have.length', 4);
  });

  it('Should display correct revenue data for all quarters', () => {
    const quarters = [1, 2, 3, 4];
    const revenues = ['€ 0.00', '€ 0.00', '€ 0.00', '€ 4,223.75'];

    // Wacht tot de tabel gevuld is (controleer op aantal rijen)
    cy.get('table tbody tr').should('have.length', 4);

    quarters.forEach((quarter, index) => {
      cy.get('table tbody tr').eq(index).within(() => {
        cy.get('td').eq(0).should('contain', quarter); // Controleer kwartaal
        cy.get('td').eq(1).should('contain', revenues[index]); // Controleer omzet
      });
    });
  });


  it('Should show "No data available" message if no data is available for the year', () => {
    cy.get('select').select('2020');
    cy.contains('Geen kwartaalgegevens beschikbaar voor 2020.').should('be.visible');
    cy.get('table').should('not.exist');
  });
});
