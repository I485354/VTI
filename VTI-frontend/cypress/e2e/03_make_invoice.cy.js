describe('New Invoice Page', () => {
  const apiUrl = 'https://vti-production.up.railway.app';
  let token = null;

  // 1) Login vóór de tests (zet token in variabele)
  before(() => {
    cy.request('POST', `${apiUrl}/api/user/login`, {
      username: 'test2',
      password: '1234'
    }).then((res) => {
      token = res.body.token; // hier bewaren we de JWT
    });
  });

  beforeEach(() => {
    cy.window().then((win) => {
      const token = win.localStorage.getItem('token');

      console.log(token);

      // 2) Maak customer aan via de beveiligde endpoint
      cy.request({
        method: 'POST',
        url: `${apiUrl}/api/admin/customer`,
        headers: {
          Authorization: `${token}` // Token meesturen!
        },
        body: {
          name: 'Jan thees',
          company: 'Jan en Piet.',
          address: '123 Main Street',
          email: 'theesses@example.com',
          phone: '1234567890'
        }
      }).then((response) => {
        const createdCustomerId = response.body.customer_id;
        cy.wrap(createdCustomerId).as('createdCustomerId'); // alias opslaan

        // 3) Haal die nieuwe customer op
        cy.request({
          method: 'GET',
          url: `${apiUrl}/api/admin/customer/customerById/${createdCustomerId}`,
          headers: {
            Authorization: `${token}`
          }
        }).then((res2) => {
          const customerId = res2.body.customer_id;
          cy.wrap(customerId).as('customerId');

          // Een paar unieke waarden voor testdata
          const uniqueChasiNumber = `XYZ123-${Date.now()}`;
          const uniquePlateNumber = `PLATE-${Date.now()}`;

          // 4) Maak een auto aan voor deze klant
          cy.request({
            method: 'POST',
            url: `${apiUrl}/api/admin/car`,
            headers: {
              Authorization: `${token}`
            },
            body: {
              customer_id: customerId,
              plate_number: uniquePlateNumber,
              brand: 'Nissan',
              model: 'GT-R',
              year: 2020,
              chasi_number: uniqueChasiNumber
            }
          }).then((carResponse) => {
            const carId = carResponse.body.car_id;
            cy.wrap(carId).as('carId');
          });

          // 5) Haal alle auto’s van deze klant op
          cy.request({
            method: 'GET',
            url: `${apiUrl}/api/admin/car/${customerId}`,
            headers: {
              Authorization: `${token}`
            }
          }).then((getCarsResponse) => {
            cy.wrap(getCarsResponse.body).as('cars');
          });
        });
      });

      // 6) Haal alle facturen op (optioneel, voor debug)
      cy.request({
        method: 'GET',
        url: `${apiUrl}/api/admin/invoice`,
        headers: {
          Authorization: `${token}`
        }
      }).then((response) => {
        cy.log(JSON.stringify(response.body));
      });

      // 7) Maak wat testproducten aan (ook beveiligde endpoint)
      cy.request({
        method: 'POST',
        url: `${apiUrl}/api/admin/product`,
        headers: {
          Authorization: `${token}`
        },
        body: {
          product_id: 1,
          name: 'Product A',
          description: 'Product A new',
          price: 25,
          quantity: 1,
          btw: 21
        }
      });

      cy.request({
        method: 'POST',
        url: `${apiUrl}/api/product`, // Let op of dit endpoint ook admin vereist
        headers: {
          Authorization: `Bearer ${token}`
        },
        body: {
          product_id: 2,
          name: 'Product B',
          description: 'Product B new',
          price: 50,
          quantity: 1,
          btw: 9
        }
      });

      // 8) Navigeer naar je front-end pagina
      // De Angular-app heeft een eigen interceptor voor /new-invoice,
      // maar we loggen hier al via de backend in. Dat is prima.
      cy.visit('/new-invoice', {failOnStatusCode: false});
    });
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
    cy.get('select#customerId').select(this.customerId.toString());
    cy.get('select#carId').select(this.carId.toString());

    // Voeg een product toe
    cy.get('#productSelect0').select('1');
    cy.get('#quantity0').clear().type('2');

    // Selecteer status en verstuur formulier
    cy.get('select#status').select('Betaald');
    cy.get('button[type="submit"]').click();

    // Retry logic for the GET request
    const retryGetInvoice = (retries = 5) => {
      cy.request('GET', `https://vti-production.up.railway.app/api/admin/invoice`).then((response) => {
        const newInvoice = response.body.find((invoice) => invoice.customer_id === this.customerId);

        if (newInvoice) {
          // Factuur gevonden, controleer de status
          expect(newInvoice).to.exist;
          expect(newInvoice.status).to.eq('Betaald');
        } else if (retries > 0) {
          // Factuur nog niet gevonden, probeer opnieuw
          cy.wait(1000); // Wacht 1 seconde
          retryGetInvoice(retries - 1);
        } else {
          // Geef op na 5 pogingen
          throw new Error('Nieuwe factuur niet gevonden na meerdere pogingen.');
        }
      });
    };

    retryGetInvoice(); // Start de retry
  });
});
