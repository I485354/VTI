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

 
    cy.visit('/new-invoice',  {failOnStatusCode: false});
  });

  it('Should load customers and allow customer selection', () => {

    cy.wait('@getCustomers');

   
    cy.get('select#customerId').should('exist');
    cy.get('select#customerId option').should('have.length.greaterThan', 0);


    cy.get('select#customerId').select('1');


    cy.contains('Naam: John Doe').should('be.visible');
    cy.contains('Adres: 123 Main Street').should('be.visible');
    cy.contains('Email: john.doe@example.com').should('be.visible');
  });

  it('Should display cars after selecting a customer', () => {

    cy.get('select#customerId').select('1');
    cy.wait('@getCarsByCustomerId');


    cy.get('select#carId').should('exist');
    cy.get('select#carId option').should('have.length.greaterThan', 0);


    cy.get('select#carId').select('1');
    cy.contains('Kenteken: AB-123-CD').should('be.visible');
    cy.contains('Merk: Toyota').should('be.visible');
    cy.contains('Model: Corolla').should('be.visible');
  });

  it('Should add a product and calculate totals correctly', () => {
    cy.wait('@getProducts');
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0', { timeout: 10000 }).should('exist');
    cy.get('#productSelect0').select('1');


    cy.get('#unitPrice0').should('have.value', '25'); 
    cy.get('#btw0').should('have.value', '21'); 
    cy.get('span#totals').should('contain', '€30.25');
  });

  it('Should remove an item and update totals', () => {
 
    cy.get('button').contains('Voeg Item Toe').click();
    cy.get('#productSelect0').should('exist'); 
    cy.get('#productSelect0').select('1');

    // Verwijder het product
    cy.get('button').contains('Verwijder').click();
    cy.get('button').contains('Verwijder').click();

   
    cy.get('span#totals').should('not.exist');
    cy.get('input#totalAmount').should('have.value', '0');
  });

  it('Should create an invoice successfully', () => {
   
    cy.get('select#customerId').select('1');
    cy.wait('@getCarsByCustomerId');
    cy.get('select#carId').select('1');


    cy.get('#productSelect0').select('1');
    cy.get('#quantity0').clear().type('2');
    
    cy.get('select#status').select('Betaald');


    cy.get('button[type="submit"]').click();
    
    cy.wait('@createInvoice').its('response.statusCode').should('eq', 201);
  });
});
