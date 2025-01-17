describe('Make Invoice', () => {
  before(() => {
    cy.visit('/login')
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');
    cy.get('button[type="submit"]').click();
    cy.wait(1000);
    cy.visit('/invoices', { failOnStatusCode: false });
  });
  it('Should create a new invoice and verify the API response', () => {
    // 1) Intercept the invoice creation endpoint
    cy.intercept('POST', '/api/admin/invoice').as('createInvoice');

    // 2) Visit the invoice creation page
    cy.visit('/new-invoice');

    // 3) Fill out the invoice form
    //    These selectors are just examples—replace them with your actual IDs or data-test attributes
    cy.get('select#customerId').select('1');      // Choose a customer
    cy.get('select#carId').select('1');           // Choose a car
    cy.get('#productSelect0').select('3');        // Choose a product
    cy.get('#quantity0').clear().type('2');       // Set quantity

    // (Any additional fields, e.g., invoice status, date, etc.)
    cy.get('select#status').select('Betaald');    // Mark as paid

    // 4) Submit the form (button with type="submit" or matching any other identifier)
    cy.get('button[type="submit"]').click();
    cy.intercept('POST', '/api/admin/invoice/create_invoice').as('createInvoice');

    // 5) Wait for the POST request to finish and assert on the response
    cy.wait('@createInvoice').then((interception) => {
      // Check status code
      expect(interception.response.statusCode).to.eq(200);

      // Example: check if response body contains an `invoiceId`
      // Adjust according to your actual response structure
      expect(interception.response.body).to.have.property('invoice_id');

      // Optionally, you can test more fields in the returned invoice
      // e.g. checking amounts, status, etc.
      expect(interception.response.body.status).to.eq('Betaald');
      expect(interception.response.body.customer_id).to.eq(1);
      expect(interception.response.body.car_id).to.eq(1);
    });
  });
});

