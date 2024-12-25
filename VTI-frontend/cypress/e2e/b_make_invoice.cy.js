describe('New Invoice Page', () => {
  beforeEach(() => {
    const apiUrl = 'http://localhost:8080';

    cy.visit('/login');
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');
    cy.get('button[type="submit"]').click();
    cy.wait(500);
    // Maak testdata via de API
    cy.request('POST', `${apiUrl}/api/customer`, {
      name: 'Jan thees',
      company: 'Jan en Piet.',
      address: '123 Main Street',
      email: 'theesses@example.com',
      phone: '1234567890'
    }).then((response) => {
      const createdCustomerId = response.body.customer_id
      cy.wrap(createdCustomerId).as('createdCustomerId');
      cy.request('GET', `${apiUrl}/api/customer/customerById/${createdCustomerId}`).then((response) => {
        const customerId = response.body.customer_id
        cy.wrap(customerId).as('customerId');

        const uniqueChasiNumber = `XYZ123-${Date.now()}`; // Uniek chassisnummer
        const uniquePlateNumber = `PLATE-${Date.now()}`; // Uniek kenteken

        cy.request('POST', `${apiUrl}/api/car`, {
          customer_id: customerId,
          plate_number: uniquePlateNumber,
          brand: 'Nissan',
          model: 'GT-R',
          year: 2020,
          chasi_number: uniqueChasiNumber
        }).then((carResponse) => {
          const carId = carResponse.body.car_id;
          cy.wrap(carId).as('carId');
        });

        cy.request('GET', `${apiUrl}/api/car/${customerId}`).then((getCarsResponse) => {
          cy.wrap(getCarsResponse.body).as('cars');
        });
      });
    });
    cy.request('GET', `${apiUrl}/api/invoice`).then((response) => {
      cy.log(JSON.stringify(response.body));
    });

    // Voeg producten toe
    cy.request('POST', `${apiUrl}/api/product`, {
      product_id: 1,
      name: 'Product A',
      description: 'Product A new',
      price: 25,
      quantity: 1,
      btw: 21
    });

    cy.request('POST', `${apiUrl}/api/product`, {
      product_id: 2,
      name: 'Product B',
      description: 'Product B new',
      price: 50,
      quantity: 1,
      btw: 9
    });


    cy.visit('/new-invoice', {failOnStatusCode: false});
  });

  it('Should load customers and allow customer selection', function () {
    // Gebruik de alias voor customerId
    cy.get('@customerId').then((customerId) => {
      cy.get('select#customerId').should('exist').select(customerId.toString());
      cy.contains('Naam: Jan thees').should('be.visible');
      cy.contains('Adres: 123 Main Street').should('be.visible');
      cy.contains('Email: theesses@example.com').should('be.visible');
    });
  });

  it('Should display cars after selecting a customer', function () {
    cy.get('@customerId').then((customerId) => {
      // Selecteer klant
      cy.get('select#customerId').select(customerId.toString());

      // Wacht tot auto's geladen zijn en controleer
      cy.get('@cars').then((cars) => {
        if(cars.length === 0){
          cy.contains('Geen auto\'s beschikbaar voor deze klant.').should('be.visible');
        } else {
          cy.get('@carId').then((carId) => {
            cy.get('select#carId').should('exist').select(carId.toString());
            cy.contains('Kenteken:').should('be.visible');
            cy.contains('Merk: Nissan').should('be.visible');
            cy.contains('Model: GT-R').should('be.visible');
          });
        }
      });
    });
  });

  it('Should add a product and calculate totals correctly', () => {
    // Voeg een product toe
    cy.get('#productSelect0').select('1');
    cy.get('#quantity0').clear().type('2');

    // Controleer de prijs, BTW en totaalbedrag
    cy.get('#unitPrice0').should('have.value', '25');
    cy.get('#btw0').should('have.value', '21');
    cy.get('span#totals').should('contain', '€60.50'); // 2x (25 + 21%)
  });

  it('Should remove an item and update totals', () => {
    // Voeg een product toe
    cy.get('#productSelect0').select('1');

    // Verwijder het product
    cy.get('button').contains('Verwijder').click();

    // Controleer dat de totalen bijgewerkt zijn
    cy.get('span#totals').should('not.exist');
    cy.get('input#totalAmount').should('have.value', '0');
  });

  it('Should create an invoice successfully', function () {
    // Selecteer klant en auto
    cy.get('select#customerId').select(this.customerId.toString());
    cy.get('select#carId').select(this.carId.toString());

    // Voeg een product toe
    cy.get('#productSelect0').select('1');
    cy.get('#quantity0').clear().type('2');

    // Selecteer status en verstuur formulier
    cy.get('select#status').select('Betaald');
    cy.get('button[type="submit"]').click();

    cy.request('GET', `http://localhost:8080/api/invoice`).then((response) => {
      const customerId = this.customerId; // Zorg dat deze alias is ingesteld
      const newInvoice = response.body.find((invoice) => invoice.customer_id === customerId);

      // Controleer dat de factuur bestaat en de status correct is
      expect(newInvoice).to.exist;
      expect(newInvoice.status).to.eq('Betaald');
    });
  });
});
