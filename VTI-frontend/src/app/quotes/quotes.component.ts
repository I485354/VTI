import { Component } from '@angular/core';
import { Products } from '../model/products.model'
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'

@Component({
  selector: 'app-quotes',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './quotes.component.html',
  styleUrls: ['./quotes.component.css']
})
export class QuotesComponent {
  products: Products[] = [
    { product_id: 1, name: 'Product A', description: 'Beschrijving van Product A', price: 50.0, quantity: 1 },
    { product_id: 2, name: 'Product B', description: 'Beschrijving van Product B', price: 75.0, quantity: 1 },
    { product_id: 3, name: 'Product C', description: 'Beschrijving van Product C', price: 100.0, quantity: 1 }
  ];

  quoteItems: Products[] = [];

  addToQuote(product: Products) {
    const productInQuote = this.quoteItems.find(item => item.product_id === product.product_id);
    if (productInQuote) {
      productInQuote.quantity += 1;
    } else {
      this.quoteItems.push({ ...product });
    }
  }

  calculateTotal(): number {
    return this.quoteItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }
}
