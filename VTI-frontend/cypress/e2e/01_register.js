describe('Register and Test Admin Endpoints', () => {
  it('Should register a new admin and do admin calls', () => {
    // 1) Ga naar de /register‐pagina
    cy.visit('/register');

    // 2) Vul een unieke username in (zodat je niet elke keer dezelfde gebruikt)
    const uniqueUsername = `testadmin_${Date.now()}`;
    cy.get('input').eq(0).type(uniqueUsername);      // username
    cy.get('input').eq(1).type('secret123');         // password
    cy.get('select').select('ADMIN');               // role

    // 3) Klik op registreren
    cy.contains('Registreer').click();

    // 4) Intercept de register‐call en pak de token (of haal hem uit localStorage)
    // Als je direct intercept wilt doen:
    cy.intercept('POST', '/api/user/register').as('registerCall');
    cy.wait('@registerCall').then((interception) => {
      const token = interception.response.body.token;
      cy.wrap(token).as('jwt');
    });

    // 5) Gebruik de token in “admin” endpoints
    cy.get('@jwt').then((jwt) => {
      cy.request({
        method: 'POST',
        url: '/api/admin/customer',
        headers: { Authorization: `Bearer ${jwt}` },
        body: {
          name: 'CypressRegisteredCustomer',
          ...
        }
      }).then((resp) => {
        expect(resp.status).to.eq(200);
      });
    });
  });
});
