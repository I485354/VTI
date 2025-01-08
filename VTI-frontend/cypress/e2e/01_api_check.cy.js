describe('API Health Check', () => {
  it('should return 200 OK', () => {
    cy.request('http://localhost:8080/').then((response) => {
      expect(response.status).to.eq(200);
    });
  });
});
