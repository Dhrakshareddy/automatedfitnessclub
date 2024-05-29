import { Component, OnInit, OnDestroy } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Subscription } from 'rxjs';
import { User } from '../../../Models/user.model';
import { UserService } from '../service/user.service';
import { Trainer } from '../../../Models/trainer.model';
import { Doctor } from '../../../Models/doctor.model';
import { DoctorService } from '../service/doctor.service';
import { TrainerService } from '../service/trainer.service';
import { MatSnackBar } from '@angular/material/snack-bar';

// import { UserService } from '../../../Services/user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit, OnDestroy {
  userInput: string = '';
  dateError: boolean = false;
  private subscription!: Subscription;
  userForm!: FormGroup;
  isEditMode = false;
  userId: any = 0;
  doctorId: FormControl = new FormControl('', [Validators.required]);
  trainerId: FormControl = new FormControl('', [Validators.required]);
  userDetails: any;
  selectedTrainer: string = "";
  selectedDoctor: string = "";
  trainer!: Trainer;
  doctor!: Doctor
  trainerDetails: any;
  doctorDetails: any;
  submittingForm: boolean = false; // Flag to track form submission

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    // private user:UserService,
    private route: ActivatedRoute,
    private router: Router,
    private trainerService: TrainerService,
    private doctorService: DoctorService,
    private snackBar: MatSnackBar,

  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get("id");
    this.userForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required, Validators.pattern(/^[a-zA-Z]+$/)]),
      password: this.formBuilder.control('', [Validators.required]),
      dateOfBirth: this.formBuilder.control(null, [Validators.required]),
      email: this.formBuilder.control('', [Validators.required]),
      contactNumber: this.formBuilder.control('', [Validators.required]),
      fitnessActivity: this.formBuilder.control('', Validators.required),
      doctorCode: this.formBuilder.control('', Validators.required),
      trainerCode: this.formBuilder.control('', Validators.required),
    });
    if (this.userId != 0 && this.userId != null) {
      this.getDetails(this.userId);
    }




  } 

  // onSubmit(): void {
  //   const user: User = this.userForm.value;
    
  //   (user);

  //   if (this.isEditMode) {
  //     user.userId = this.userId;
  //   }


  //   this.userService.saveUser(user).subscribe(() => {
  //     this.router.navigate(['/admin/users/add/historymedical']);
  //   });
  // }

  onSubmit(): void {
    this.submittingForm = true; // Set flag when form is submitted
    const user: User = this.userForm.value;
    (user);

    if (this.isEditMode) {
      user.userId = this.userId;
    }
    // Make an HTTP request to save user data
    this.userService.saveUser(user).subscribe(
      () => {
        this.showSnackBar('User details added successfully!');
        this.router.navigate(['/admin/users/add/historymedical']);
        // Success callback
        // Navigate or perform actions after successful submission
  
        // Reset the form and flag after 3 seconds
        setTimeout(() => {
          this.submittingForm = false;
          this.userForm.reset();
        }, 3000);
      },
      (error) => {
        // Error callback
        console.error('Error:', error);
  
        // Handle error scenarios, if any
        // Optionally, you can reset the flag and display an error message
        this.submittingForm = false;
      }
    );
  }

  futureDateValidator(control: AbstractControl): ValidationErrors | null {
    const selectedDate = control.value;
    const currentDate = new Date();

    if (selectedDate > currentDate) {
      return { futureDate: true };
    }

    return null;
  }

  update() {
    const user: User = this.userForm.value;
    user.userId = this.userDetails.userId

    this.userService.updateuser(user).subscribe(() => {
      this.showSnackBar('User details updated successfully!');
      this.router.navigate(['/admin/users']);
    });
  }

  getDetails(userId: any): void {
    if (userId != null) {
      this.userService.getUserById(userId).subscribe(
        (data: any) => {
          this.userDetails = data;
          (JSON.stringify(this.userDetails))
          this.userForm.patchValue(this.userDetails);
        }, (error: any) => {
          (error)
        }

      )
    }
  }

  validateDate() {
    const selectedDate = new Date(this.userForm.get('dateOfBirth')?.value);

    if (selectedDate.getFullYear() > 2006) {
      this.dateError = true;
    } else {
      this.dateError = false;
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
