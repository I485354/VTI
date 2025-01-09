describe('API Health Check', () => {
  it('should return 200 OK', () => {
    cy.request('https://vti-production.up.railway.app/').then((response) => {
      expect(response.status).to.eq(200);
    });
  });
});
