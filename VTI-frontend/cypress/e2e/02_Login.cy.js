describe('Login', () => {
  it('should login correctly and navigate to the dashboard', () => {
    // Vul de gebruikersnaam en het wachtwoord in
    cy.request({
      method: 'POST',
      url: 'http://localhost:8080/api/user/register',
      body: {
        username: 'test2',
        password: '1234',
      },
      failOnStatusCode: false
    }).then((response) => {
      if (response.status === 201) {
        // Gebruiker is zojuist aangemaakt
        cy.log('User created successfully');
      } else if (response.status === 403) {
        // Blijkbaar bestond de user al of geen toestemming
        cy.log('User already exists or no permission -> 403');
      } else {
        cy.log('Unexpected status: ' + response.status);
      }
    });

    cy.visit('/login');

    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');


    cy.get('button[type="submit"]').click();

    cy.url().should('include', '/dashboard');
    cy.contains('Dashboard').should('be.visible');
  });

  it('should show an error if username already exists', () => {
    cy.request({
      method: 'POST',
      url: 'http://localhost:8080/api/user/register',
      body: {
        username: 'test2',
        password: '1234',
      },
      failOnStatusCode: false
    }).then((response) => {
      expect(response.status).to.be.oneOf([403]);
      //expect(response.body).to.contain('Username already exists')
    });
  });
});
