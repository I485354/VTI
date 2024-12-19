import { mount } from 'cypress/angular';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { SettingsComponent } from './settings.component'

describe('Settings Component', () => {
  beforeEach(() => {
    mount(SettingsComponent, {
      imports: [HttpClientTestingModule, FormsModule],
    })
  });

  
  it('should render the settings page', () => {
    // Controleer of de pagina geladen is
    cy.get('h1').contains('Instellingen').should('be.visible'); // Controleer of de titel 'Instellingen' aanwezig is
  });
 

});
