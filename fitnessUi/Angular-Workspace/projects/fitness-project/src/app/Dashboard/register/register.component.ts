import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

import { ActivatedRoute, Router } from '@angular/router';

import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

import { UserService } from '../../Admin/service/user.service';

import { User } from '../../../Models/user.model';
import { Trainer } from '../../../Models/trainerprofile.model';
import { Doctor } from '../../../Models/doctor.model';
import { TrainerService } from '../../../Services/trainer.service';
import { DoctorService } from '../../../Services/doctor.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  private subscription!: Subscription;
  userForm!: FormGroup;
  isEditMode = false;
  userId: any;
  doctorId: FormControl = new FormControl('', [Validators.required]);
  trainerId: FormControl = new FormControl('', [Validators.required]);
  selectedTrainer: string = "";
  selectedDoctor: string = "";
  trainer!: Trainer;
  doctor!: Doctor
  trainerDetails: any;
  doctorDetails: any;
  auth: any;
  userInput: string = '';
  dateError: boolean = false;
  submittingForm: boolean = false; // Variable to track form submission

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private trainerService: TrainerService,
    private doctorService: DoctorService,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
      dob: ['', Validators.required],
      email: ['', Validators.required],
      contactNumber: ['', Validators.required],
      doctorId: ['', Validators.required],
      TrainerId: ['', Validators.required],
    });
  }

  onSubmit(user: User): void {
    this.submittingForm = true; // Set to true when form is being submitted

    // Your existing code for form submission...
    this.userService.saveUser(user).subscribe(
      (response: any) => {
        const json = response.JSON;

        if (response != null) {
          if (response.role === 'USER') {
            localStorage.setItem("user", JSON.stringify(response));
            this.showSnackBar("submitted successfully");
            this.router.navigate(['/userlogin/payments']);
          }
        }
      },
      (error: any) => {
        console.error('Error:', error);
        if (error.status === 401) {
          console.error('Unauthorized - Incorrect credentials');
        } else {
          console.error('Login failed - Unexpected error');
        }
      }
    );

    setTimeout(() => {
      this.submittingForm = false; // Reset to false after 3 seconds (simulating response delay)
    }, 3000);
  }

  fetchTrainerDetails() {
    this.trainerService.fetchTrainerByCode(this.selectedTrainer).subscribe((trainer) => {
      this.trainerDetails = trainer
    })
  }

  fetchDoctorDetails() {
    this.doctorService.fetchDoctorByCode(this.selectedDoctor).subscribe((doctor) => {
      this.doctorDetails = doctor
    })
  }

  private showSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.panelClass = ['custom-snackbar'];
    config.duration = 2000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, 'close', config);
  }

  validateDate() {
    const selectedDate = new Date(this.userForm.get('dateOfBirth')?.value);

    if (selectedDate.getFullYear() > 2006) {
      this.dateError = true;
    } else {
      this.dateError = false;
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}

