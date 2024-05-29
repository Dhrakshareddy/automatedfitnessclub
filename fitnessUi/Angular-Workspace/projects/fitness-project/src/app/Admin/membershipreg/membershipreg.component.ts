import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MembershipService } from '../service/membership.service';
import { Membership } from '../model/membership.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-membershipreg',
  templateUrl: './membershipreg.component.html',
  styleUrl: './membershipreg.component.css'
})


export class MembershipregComponent implements OnInit, OnDestroy {
  private subscription!: Subscription;
  membershipForm!: FormGroup;

  isEditMode: any;
  membershipId: any;

  membershipDetails: any;


  constructor(private fb: FormBuilder, private membershipService: MembershipService, private route: ActivatedRoute, private router: Router,private snackBar:MatSnackBar) {
    this.createForm();
  }


  ngOnInit(): void {
    this.membershipId = this.route.snapshot.paramMap.get("id");
    this.membershipForm = this.fb.group({

      membershipType: ['', Validators.required],
      period: ['', Validators.required],
      description: ['', Validators.required],

    });
    if (this.membershipId != 0 && this.membershipId != null) {
      this.getDetails(this.membershipId);
    }




  }


  createForm() {
    this.membershipForm = this.fb.group({

      membershipType: ['', Validators.required],
      period: ['', Validators.required],
      description: ['', Validators.required],




    });
  }

  onSubmit(): void {
    const membership: Membership = this.membershipForm.value;
    (membership);

    if (this.isEditMode) {
      membership.membershipId = this.membershipId;
    }




    this.membershipService.saveMembership(membership).subscribe(() => {
      this.showSnackBar('Added successfully!');
      this.router.navigate(['/admin/membership']);
    });
  }


  update() {
    const membership: Membership = this.membershipForm.value;
    membership.membershipId = this.membershipDetails.membershipId

    this.membershipService.updatemembership(membership).subscribe(() => {
      this.showSnackBar('updated successfully!');
      this.router.navigate(['/admin/membership']);
    });
  }



  getDetails(userId: any): void {
    if (this.membershipId != null) {
      this.membershipService.getMembershipById(this.membershipId).subscribe(
        (data: any) => {
          this.membershipDetails = data;
          (JSON.stringify(this.membershipDetails))
          this.membershipForm.patchValue(this.membershipDetails);
        }, (error: any) => {
          (error)
        }

      )
    }
  }
   showSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, 
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }


  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}