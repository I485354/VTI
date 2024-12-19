import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

import { Products } from '../model/products.model';

import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css'
})
export class ProductFormComponent {
  newProduct: Products = { product_id: 0, name: '', description: '', price: 0, quantity: 1, btw: 21 };
  constructor(private apiService: ApiService, private router: Router) {}

  addProduct() {
    this.apiService.addProduct(this.newProduct).subscribe(
      (response) => {
        console.log('Product toegevoegd:', this.newProduct);
        console.log(response);
        setTimeout(() => {
          this.router.navigate(['/quotes']);
          this.clearForm()
          }, 3000
        );

      },
      (error) => {
        console.error('Fout bij het toevoegen van product:', error);
      }
    );
  }

  clearForm() {
    this.newProduct = { product_id: 0, name: '', description: '', price: 0, quantity: 1, btw: 21 };
  }
}
