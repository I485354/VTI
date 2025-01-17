const testCustomer = {
  name: 'John Doe',
  email: 'john.doe@example.com',
  company: 'Doe Enterprises',
  phone: '0612345678',
  address: 'Nieuwe straat 1',
};

describe('Customer List', () => {
  beforeEach(() => {
    // 1) Log in via UI or by request (whichever you prefer)
    cy.visit('/login');
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');
    cy.get('button[type="submit"]').click();

    // 2) Wait a bit or intercept the login XHR if needed
    cy.wait(1000);

    // 3) Intercept the GET request for customers
    cy.intercept('GET', '**/api/admin/customer').as('getCustomers');

    // 4) Now visit the customer list
    cy.visit('/customer-list', { failOnStatusCode: false });

    // 5) Wait for the GET request to complete and verify the response
    cy.wait('@getCustomers').then((interception) => {
      expect(interception.response.statusCode).to.eq(200);
      // Optionally check that the response body is an array, etc.
      // expect(interception.response.body).to.be.an('array');
    });
  });

  it('Should add a new customer and display it in the list', () => {
    // 1) Intercept the POST request for creating a customer
    cy.intercept('POST', '**/api/admin/customer').as('createCustomer');

    // 2) Click on "Klant Toevoegen" to open form
    cy.contains('Klant Toevoegen').click();

    // 3) Fill out the form
    cy.get('input#name').type(testCustomer.name);
    cy.get('input#email').type(testCustomer.email);
    cy.get('input#company').type(testCustomer.company);
    cy.get('input#phone').type(testCustomer.phone);
    cy.get('input#address').type(testCustomer.address);

    // 4) Submit the form
    cy.contains('Opslaan').click();
    cy.contains('Nieuwe klant aangemaakt').should('be.visible');
    cy.wait(3000);

    // 5) Wait for the POST request to finish
    cy.wait('@createCustomer').then((interception) => {
      // Check status code
      expect(interception.response.statusCode).to.eq(201);
      // Optionally verify the response body
      // e.g. expect(interception.response.body.name).to.eq(testCustomer.name);
    });

    // 6) Check UI feedback


    // 7) Confirm the newly added customer appears in the table
    cy.contains(testCustomer.name).should('be.visible');
    cy.contains(testCustomer.email).should('be.visible');
    cy.contains(testCustomer.company).should('be.visible');
    cy.contains(testCustomer.phone).should('be.visible');
    cy.contains(testCustomer.address).should('be.visible');
  });

  it('Should open the edit form with customer info filled in', () => {
    // We assume the page has loaded customers already in the table
    // and "testCustomer" exists there.
    cy.contains(testCustomer.name)
      .parent()
      .find('button.edit-button')
      .click();

    // Check that the existing info is populated in the form
    cy.get('input#name').should('have.value', testCustomer.name);
    cy.get('input#email').should('have.value', testCustomer.email);
    cy.get('input#company').should('have.value', testCustomer.company);
    cy.get('input#phone').should('have.value', testCustomer.phone);
    cy.get('input#address').should('have.value', testCustomer.address);
  });

  it('Should edit and save customer details', () => {
    const updatedCustomer = {
      name: 'Jane Doe',
      email: 'jane.doe@example.com',
      company: 'Doe Updated Enterprises',
      phone: '0612349999',
      address: 'Updated Straat 2',
    };

    // 1) Intercept the PUT or POST request used to update the customer
    //    (depends on your backend logic; adjust as needed)
    cy.intercept('PUT', '**/api/admin/customer/*').as('updateCustomer');

    // 2) Click Edit
    cy.contains(testCustomer.name)
      .parent()
      .find('button.edit-button')
      .click();

    // 3) Clear & update fields
    cy.get('input#name').clear().type(updatedCustomer.name);
    cy.get('input#email').clear().type(updatedCustomer.email);
    cy.get('input#company').clear().type(updatedCustomer.company);
    cy.get('input#phone').clear().type(updatedCustomer.phone);
    cy.get('input#address').clear().type(updatedCustomer.address);

    // 4) Save changes
    cy.contains('Opslaan').click();
    cy.wait(3500);

    // 5) Wait for the update request to finish
    cy.wait('@updateCustomer').then((interception) => {
      expect(interception.response.statusCode).to.eq(201);
      // Optionally verify the response body has new fields
      // e.g. expect(interception.response.body.phone).to.eq('0612349999');
    });

    // 6) Confirm UI is updated
    cy.contains(updatedCustomer.name).should('be.visible');
    cy.contains(updatedCustomer.email).should('be.visible');
    cy.contains(updatedCustomer.company).should('be.visible');
    cy.contains(updatedCustomer.phone).should('be.visible');
    cy.contains(updatedCustomer.address).should('be.visible');
  });

  it('Should cancel editing customer details', () => {
    // 1) Click any “Bewerken” button
    cy.contains('Bewerken').should('be.visible').click();

    // 2) Click “Annuleren”
    cy.contains('Annuleren').click();

    // 3) Confirm the edit form is closed (no “Opslaan” button)
    cy.contains('Opslaan').should('not.exist');
  });

  it('Should delete a customer', () => {
    const randomName = `John Doe ${Date.now()}`;
    cy.intercept('POST', '**/api/admin/customer').as('createCustomer');

    // 2) Click on "Klant Toevoegen" to open form
    cy.contains('Klant Toevoegen').click();

    // 3) Fill out the form
    cy.get('input#name').type(randomName);
    cy.get('input#email').type(testCustomer.email);
    cy.get('input#company').type(testCustomer.company);
    cy.get('input#phone').type(testCustomer.phone);
    cy.get('input#address').type(testCustomer.address);

    // 4) Submit the form
    cy.contains('Opslaan').click();
    cy.contains('Nieuwe klant aangemaakt').should('be.visible');
    cy.wait(3000);

    // 5) Wait for the POST request to finish
    cy.wait('@createCustomer').then((interception) => {
      // Check status code
      expect(interception.response.statusCode).to.eq(201);

      // 2) Visit the page that shows customers
      cy.intercept('GET', '**/api/admin/customer').as('getCustomers');
      cy.visit('/customer-list');
      // 3) Wait for the GET request that populates the customer list
      cy.wait('@getCustomers');

      // 4) Intercept the DELETE request
      cy.intercept('DELETE', '**/api/admin/customer/*').as('deleteCustomer');

      // 5) Locate the newly created customer by name in the UI
      cy.contains(randomName)
        .parent()
        .find('button.delete-button')
        .click();

      // 6) Confirm the modal
      cy.contains(`Weet je zeker dat je ${randomName} wilt verwijderen?`).should('be.visible');
      cy.get('button.confirm-button').click();

      // 7) Wait for the DELETE request to confirm success
      cy.wait('@deleteCustomer').then((interception) => {
        expect(interception.response.statusCode).to.eq(200);
      });
      cy.wait(1500);

      // 8) Ensure the customer’s name is no longer in the list
      cy.contains(randomName).should('not.exist');
    });
  });
});

