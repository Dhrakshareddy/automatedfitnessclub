import { Component, OnDestroy, OnInit } from '@angular/core';
import { MembershipService } from '../service/membership.service';
import { Membership } from '../model/membership.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-membership',
  templateUrl: './membership.component.html',
  styleUrl: './membership.component.css'
})
export class MembershipComponent implements OnInit,OnDestroy {
  membership: Membership[] = [];
  router: any;
  private  subscription!:Subscription

  constructor(private membershipService: MembershipService) { }
  ngOnInit(): void {
    this.getMembership();
  }

  deleteMembership(id: number): void {
    

    this.membershipService.deleteMembership(id).subscribe((res) => {

      this.getMembership();
    });
  }
  getMembership() {
    this.membershipService.getMembership().subscribe((membership: Membership[]) => {


      this.membership = membership

    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();

    }
  }
}
