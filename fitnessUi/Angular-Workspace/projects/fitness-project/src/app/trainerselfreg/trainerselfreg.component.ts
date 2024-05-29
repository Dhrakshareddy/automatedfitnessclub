import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { TrainerService } from '../Admin/service/trainer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Trainer } from '../Admin/model/trainer.model';

@Component({
  selector: 'app-trainerselfreg',
  templateUrl: './trainerselfreg.component.html',
  styleUrl: './trainerselfreg.component.css'
})
export class TrainerselfregComponent implements OnInit, OnDestroy {
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
  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

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
  
  showSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, // Duration in milliseconds
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  } 
  getDetails(trainerId: any) {
    throw new Error('Method not implemented.');
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



}
