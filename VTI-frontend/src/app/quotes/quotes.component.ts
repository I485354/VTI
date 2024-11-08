import { Component, OnInit } from '@angular/core';
import { Products } from '../model/products.model'
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from '../api.service';


@Component({
  selector: 'app-quotes',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './quotes.component.html',
  styleUrls: ['./quotes.component.css']
})
export class QuotesComponent implements OnInit {
  products: Products[] = [];
  quoteItems: Products[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getProducts().subscribe((data: Products[]) => {
      this.products = data;
    });
  }

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
