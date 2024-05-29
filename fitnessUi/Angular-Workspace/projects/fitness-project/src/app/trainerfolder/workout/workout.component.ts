import { Component, OnDestroy, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkoutService } from '../../../Services/workout.service';
import { Workout } from '../../../Models/workout.model';
import { MatSnackBar } from '@angular/material/snack-bar';





@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrl: './workout.component.css'
})
export class WorkoutComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  user: any;
  getUserName: any;
  
  workout: Workout = {
    createdBy: undefined,
    workoutId: 0,
    workoutDate: new Date(),
    duration: '',
    setsCompleted: 0,
    username: ''
  };
    constructor(private route: ActivatedRoute,private workoutService: WorkoutService,private router:Router, private snackBar: MatSnackBar) {}
  ngOnInit(): void {
    this.getUserName=localStorage.getItem("user");
    if(this.getUserName)
    {
    this.getUserName=JSON.parse(this.getUserName);
    this.getUserName.name;
    this.workout.username = this.getUserName.name;;
    }
    else{
      ("workout not found");
    }
  }
  
  saveWorkout(): void {
  
    this.workoutService.saveWorkout(this.workout).subscribe(
      (response) => {
        
        this.showSnackBar('workout  saved successfully!');
        this.router.navigate(['/verticalnav/workouts']);
      },
      (error) => {
        console.error('Error saving attendance:', error);
      }
    );
  }
  
  showSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    });
  }
  
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}