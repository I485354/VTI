import { mount } from 'cypress/angular';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

describe('AppComponent', () => {
  beforeEach(() => {
    mount(AppComponent, {
      imports: [HttpClientModule, RouterTestingModule, SidebarComponent],
    });
  });
  
  it('should contain a router outlet', () => {
    // Controleer of RouterOutlet bestaat
    cy.get('router-outlet').should('exist');
  });
});


