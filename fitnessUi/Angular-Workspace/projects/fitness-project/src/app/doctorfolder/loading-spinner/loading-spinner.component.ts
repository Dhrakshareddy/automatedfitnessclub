// loading-spinner.component.ts

import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { LoadingSpinnerAnimation } from '../../../Services/loading-spinner.animations';
import { Subscription } from 'rxjs';




@Component({
  selector: 'app-loading-spinner',
  templateUrl: './loading-spinner.component.html',
  styleUrls: ['./loading-spinner.component.css'],
  animations: [LoadingSpinnerAnimation] 
})
export class LoadingSpinnerComponent implements OnInit,OnDestroy {
  @Input() showSpinner = false;
  private subscription!:Subscription

  ngOnInit(): void {
    setTimeout(() => {
      this.showSpinner = false;
    }, 3000);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
