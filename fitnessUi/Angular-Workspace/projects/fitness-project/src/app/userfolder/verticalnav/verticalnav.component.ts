import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-verticalnav',
 
  templateUrl: './verticalnav.component.html',
  styleUrl: './verticalnav.component.css'
})
export class VerticalnavComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  isMenuOpen: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  
}
