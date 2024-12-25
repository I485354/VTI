const testCustomer = {
  name: 'John Doe',
  email: 'john.doe@example.com',
  company: 'Doe Enterprises',
  phone: '0612345678',
  address: 'Nieuwe straat 1',
};

describe('Customer List', () => {
  beforeEach(() => {
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');

    cy.get('button[type="submit"]').click();
    cy.visit('/customer-list', { failOnStatusCode: false });
  });

  it('Should add a new customer and display it in the list', () => {
    // Klik op de "Klant Toevoegen" knop
    cy.contains('Klant Toevoegen').click();

    // Vul het formulier in met klantinformatie
    cy.get('input#name').type(testCustomer.name);
    cy.get('input#email').type(testCustomer.email);
    cy.get('input#company').type(testCustomer.company);
    cy.get('input#phone').type(testCustomer.phone);
    cy.get('input#address').type(testCustomer.address);

    // Klik op de "Opslaan" knop
    cy.contains('Opslaan').click();
    cy.contains(`Nieuwe klant aangemaakt`).should('be.visible');
    cy.wait(5000);
    cy.contains(testCustomer.name).should('be.visible');
    cy.contains(testCustomer.email).should('be.visible');
    cy.contains(testCustomer.company).should('be.visible');
    cy.contains(testCustomer.phone).should('be.visible');
    cy.contains(testCustomer.address).should('be.visible');
  });

  it('Should open the edit form with customer info filled in', () => {
    cy.contains(testCustomer.name).parent().find('button.edit-button').click();
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

      cy.contains(testCustomer.name).parent().find('button.edit-button').click();

      cy.get('input#name').clear().type(updatedCustomer.name);
      cy.get('input#email').clear().type(updatedCustomer.email);
      cy.get('input#company').clear().type(updatedCustomer.company);
      cy.get('input#phone').clear().type(updatedCustomer.phone);
      cy.get('input#address').clear().type(updatedCustomer.address);

      // Klik op "Opslaan"
      cy.contains('Opslaan').click();
      cy.wait(5000);

      // Controleer of de lijst de geüpdatete klant toont
      cy.contains(updatedCustomer.name).should('be.visible');
      cy.contains(updatedCustomer.email).should('be.visible');
      cy.contains(updatedCustomer.company).should('be.visible');
      cy.contains(updatedCustomer.phone).should('be.visible');
      cy.contains(updatedCustomer.address).should('be.visible');
  });

  it('Should cancel editing customer details', () => {
      cy.contains('Bewerken').should('be.visible').click();
      cy.contains('Annuleren').click();
      cy.wait(2000);
      cy.contains('Opslaan').should('not.exist');
  });

  it('Should delete a customer', () => {
    cy.request('POST' , 'http://localhost:8080/api/customer', {
        name: 'John Doe',
        email: 'john.doe@example.com',
        company: 'Doe Enterprises',
        phone: '0612345678',
        address: 'Nieuwe straat 1',
    }).then(response => {
      const newCustomer = response.body;
      cy.wrap(newCustomer).as('customer');
    })
    cy.visit('/customer-list');
    cy.get('@customer').then((customer) => {

      cy.contains(customer.name).parent().find('button.delete-button').click();
      cy.contains(`Weet je zeker dat je ${customer.name} wilt verwijderen?`).should('be.visible');
      cy.get('button.confirm-button').click();
      cy.wait(2500)

      cy.contains(customer.name).should('not.exist');
    });
  });
});

