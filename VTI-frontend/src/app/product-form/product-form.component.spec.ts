import { mount } from 'cypress/angular';
import { ProductFormComponent } from './product-form.component';
import { FormsModule } from '@angular/forms'; // Voeg FormsModule toe als het formulier NgModel gebruikt
import { HttpClientTestingModule } from '@angular/common/http/testing'; // Voor HTTP functionaliteit

describe('ProductFormComponent', () => {
  beforeEach(() => {
    // Mount de component met benodigde modules
    mount(ProductFormComponent, {
      imports: [FormsModule, HttpClientTestingModule], // Voeg FormsModule en HttpClientTestingModule toe
    });
  });

  it('should render the product form', () => {
    // Controleer of het formulier zichtbaar is
    cy.visit('/product-form');
  });
  
});
