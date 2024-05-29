import { Component, OnDestroy, OnInit } from '@angular/core';
import { Payments } from './payment.model';
import { PaymentService } from './Payment.service';
import { AuthService } from '../../Dashboard/dash-board/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-fetchpayment',
  templateUrl: './fetchpayment.component.html',
  styleUrl: './fetchpayment.component.css'
})
export class FetchpaymentComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  userName: string = ''; 
  payment: Payments[] = [];

  constructor(private paymentService : PaymentService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().name;
    // alert(this.userName)
    this.fetchProgress();
  }

  fetchProgress() {
  
    this.paymentService.getUserByName(this.userName)
      .subscribe((data: Payments[]) => {
        (data); 
        this.payment = data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
