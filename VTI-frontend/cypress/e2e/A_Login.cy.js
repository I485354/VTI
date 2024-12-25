describe('Login', () => {
  beforeEach(() => {
    cy.request('POST', 'http://localhost:8080/api/user/register', {
        username: 'test2',
        password: '1234',
    });
    cy.visit('/login');
  });

  it('should login correctly and navigate to the dashboard', () => {
    // Vul de gebruikersnaam en het wachtwoord in
    cy.get('input[name="username"]').type('test2');
    cy.get('input[name="password"]').type('1234');

    // Klik op de login-knop
    cy.get('button[type="submit"]').click();

    // Controleer of de gebruiker naar de dashboardpagina wordt doorgestuurd
    cy.url().should('include', '/dashboard');

    // Controleer of het dashboard een bepaald element bevat, bijvoorbeeld een welkomstbericht
    cy.contains('Dashboard').should('be.visible');
  });
});
