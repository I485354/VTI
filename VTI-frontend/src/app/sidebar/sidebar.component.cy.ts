import { mount } from 'cypress/angular';
import { SidebarComponent } from './sidebar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';

describe('SidebarComponent', () => {
  beforeEach(() => {
    mount(SidebarComponent, {
      imports: [CommonModule, FontAwesomeModule, RouterTestingModule],
    });
  });

  it('should render the sidebar with all navigation links', () => {
    // Controleer of de sidebar bestaat
    cy.get('nav.sidebar').should('exist');

    // Controleer of alle navigatielinks zichtbaar zijn
    const navLinks = [
      { text: 'Dashboard', link: '/dashboard' },
      { text: 'Facturen', link: '/invoices' },
      { text: 'Offertes', link: '/quotes' },
      { text: 'Klanten', link: '/customer-list' },
      { text: 'Betalingen', link: '/payments' },
      { text: 'Instellingen', link: '/settings' },
    ];

    navLinks.forEach((item) => {
      cy.contains('.nav-text', item.text).should('be.visible');
    });
  });

  it('should toggle sidebar collapsed state', () => {
    // Sidebar is standaard niet ingeklapt
    cy.get('nav.sidebar').should('not.have.class', 'collapsed');

    // Klik op de toggle-knop om de sidebar in te klappen
    cy.get('.toggle-btn').click();
    cy.get('nav.sidebar').should('have.class', 'collapsed');

    // Klik opnieuw om de sidebar uit te klappen
    cy.get('.toggle-btn').click();
    cy.get('nav.sidebar').should('not.have.class', 'collapsed');
  });

  it('should hide navigation text when collapsed', () => {
    // Klik om in te klappen
    cy.get('.toggle-btn').click();

    // Klik om uit te klappen
    cy.get('.toggle-btn').click();
    cy.get('.nav-text').should('be.visible');
  });
});
