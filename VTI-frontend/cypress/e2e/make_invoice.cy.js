describe('New Invoice Page', () => {
  beforeEach(() => {
    // Mock API-aanroepen met fixtures
    cy.intercept('GET', '/api/customer', (req) => {
      console.log('Intercepted request:', req);
    }).as('getCustomers');
    cy.intercept('GET', '/api/customer', { fixture: 'customer.json'
    }).as('getCustomers');

    cy.intercept('GET', '/api/car/*', { fixture: 'car.json' }).as('getCarsByCustomerId');

    cy.intercept('GET', '/api/product', { fixture: 'product.json' }).as('getProducts');

    cy.intercept('POST', '/api/invoice/create_invoice', { statusCode: 201 }).as('createInvoice');

    // Bezoek de nieuwe factuurpagina
    cy.visit('/new-invoice',  {failOnStatusCode: false});
  });

  it('Should load customers and allow customer selection', () => {
    // Wacht tot klanten geladen zijn
    cy.wait('@getCustomers');

    // Controleer of de klanten-dropdown zichtbaar is
    cy.get('select#customerId').should('exist');
    cy.get('select#customerId option').should('have.length.greaterThan', 0);

    // Selecteer een klant (bijvoorbeeld de eerste klant)
    cy.get('select#customerId').select('1');

    // Controleer of klantgegevens correct worden weergegeven
    cy.contains('Naam: John Doe').should('be.visible');
    cy.contains('Adres: 123 Main Street').should('be.visible');
    cy.contains('Email: john.doe@example.com').should('be.visible');
  });

  it('Should display cars after selecting a customer', () => {
    // Selecteer een klant
    cy.get('select#customerId').select('1');
    cy.wait('@getCarsByCustomerId');

    // Controleer of de auto-dropdown zichtbaar is
    cy.get('select#carId').should('exist');
    cy.get('select#carId option').should('have.length.greaterThan', 0);

    // Controleer of de juiste auto-opties worden weergegeven
    cy.get('select#carId').select('1');
    cy.contains('Kenteken: AB-123-CD').should('be.visible');
    cy.contains('Merk: Toyota').should('be.visible');
    cy.contains('Model: Corolla').should('be.visible');
  });

  it('Should add a product and calculate totals correctly', () => {
    cy.wait('@getProducts');
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0', { timeout: 10000 }).should('exist'); // Wacht tot het element bestaat
    cy.get('#productSelect0').select('1'); // Selecteer een product


    cy.get('#unitPrice0').should('have.value', '25'); // Eenheidsprijs
    cy.get('#btw0').should('have.value', '21'); // BTW
    cy.get('span#totals').should('contain', '€30.25'); // Totaal inclusief BTW
  });

  it('Should remove an item and update totals', () => {
    // Voeg een product toe
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0').should('exist'); // Controleer het eerste select-element
    cy.get('#productSelect0').select('1');

    // Verwijder het product
    cy.get('button').contains('Verwijder').click();
    cy.get('button').contains('Verwijder').click();

    // Controleer dat het item is verwijderd en het totaal is bijgewerkt
    cy.get('span#totals').should('not.exist');
    cy.get('input#totalAmount').should('have.value', '0');
  });

  it('Should create an invoice successfully', () => {
    // Selecteer klant en auto
    cy.get('select#customerId').select('1');
    cy.wait('@getCarsByCustomerId');
    cy.get('select#carId').select('1');


    cy.get('#productSelect0').select('1');
    cy.get('#quantity0').clear().type('2');

    // Kies de factuurstatus
    cy.get('select#status').select('Betaald');

    // Klik op "Factuur aanmaken"
    cy.get('button[type="submit"]').click();

    // Wacht op de API-aanroep en controleer het resultaat
    cy.wait('@createInvoice').its('response.statusCode').should('eq', 201);
  });
});
