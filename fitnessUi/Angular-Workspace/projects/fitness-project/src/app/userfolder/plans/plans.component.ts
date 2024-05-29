
import { Component } from '@angular/core';

@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.css']
})
export class PlansComponent {
  isChecked = false;
  isButtonDisabled = false;

  enableCheckbox() {
    this.isChecked = true;
    this.isButtonDisabled = true;
  }

  removeButtons() {
    this.isChecked = false;
    this.isButtonDisabled = false;
  }
  fitnessPlans = [
    {
      name: 'Pro Plan',
      description: 'Access to basic workout routines and nutrition tips.',
      price: 29.99
    },
    
  ];

}

