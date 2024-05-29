import { Component } from '@angular/core';

@Component({
  selector: 'app-cartdetails',
  templateUrl: './cartdetails.component.html',
  styleUrl: './cartdetails.component.css'
})
export class CartdetailsComponent {
  cart = [
    { id: 1, name: 'Product 1', price: 20.00, quantity: 1 },
   
  ];

  increaseQuantity(product: any) {
    product.quantity++;
  }

  decreaseQuantity(product: any) {
    if (product.quantity > 1) {
      product.quantity--;
    }
  }

  calculateTotal() {
   
  }
}
