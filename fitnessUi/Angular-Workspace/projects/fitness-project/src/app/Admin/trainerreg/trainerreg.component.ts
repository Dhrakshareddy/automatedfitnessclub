import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';



import { TrainerService } from '../service/trainer.service';
import { Trainer } from '../model/trainer.model';
import { MatSnackBar } from '@angular/material/snack-bar';




@Component({
  selector: 'app-trainerreg',

  templateUrl: './trainerreg.component.html',
  styleUrl: './trainerreg.component.css'
})
export class TrainerregComponent implements OnInit, OnDestroy {
  private subscription!: Subscription;
  trainerForm!: FormGroup;
  isEditMode = false;

  trainerDetails: any;
  trainerId: any;



  constructor(
    private formBuilder: FormBuilder,
    private trainerService: TrainerService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar:MatSnackBar
  ) { }

  ngOnInit(): void {
    this.trainerId = this.route.snapshot.paramMap.get("id");
    this.trainerForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
      age: ['', Validators.required],
      yearOfExperience: ['', Validators.required],
      shiftTimings: ['', Validators.required],


      contactNumber: ['', Validators.required],
      trainerCode: ['', Validators.required],
    });

    if (this.trainerId != 0 && this.trainerId != null) {
      this.getDetails(this.trainerId);
    }


    this.subscription = this.route.paramMap.subscribe(params => {
      const id = +params.get('id')!;
      if (!isNaN(id)) {
        this.isEditMode = true;
        this.trainerId = id;

        this.trainerService.getTrainerById(id).subscribe((trainer: Trainer) => {
          this.trainerForm.patchValue(trainer);
        });

      }
    });


  } 

    onSubmit(): void {
      const trainer: Trainer = this.trainerForm.value;
      (trainer);

      if (this.isEditMode) {
        trainer.trainerId = this.trainerId;
      }


      this.trainerService.saveTrainer(trainer).subscribe(() => {
        this.showSnackBar('Trainer added successfully!');
        this.router.navigate(['/admin/trainers']);
      });
    }


    update() {
      const trainer: Trainer = this.trainerForm.value;
      trainer.trainerId = this.trainerDetails.trainerId

      this.trainerService.updatetrainer(trainer).subscribe(() => {
        this.showSnackBar('Trainer updated successfully!');
        this.router.navigate(['/admin/trainers']);
      });
    }



  getDetails(userId: any): void {
    if (userId != null) {
      this.trainerService.getTrainerById(userId).subscribe(
        (data: any) => {
          this.trainerDetails = data;

          this.trainerForm.patchValue(this.trainerDetails);
        }, (error: any) => {
          console.error(error)
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