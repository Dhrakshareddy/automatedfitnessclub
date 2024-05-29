import { Component, OnDestroy, OnInit } from '@angular/core';


import { ExerciseService } from '../../../Services/exercise.service';

import { ActivatedRoute, Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';
import { Exercise } from '../../../Models/exercise.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css'] 
})
export class ExerciseComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  exercise: Exercise = {
    exerciseId: 0,
    exerciseName: '',
    description: '',
    equipmentNeeded: '',
    numberOfSets: 0,
    username: ''
  };


  constructor(
    private exerciseService: ExerciseService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router
  ) {}

    ngOnInit():void{
      this.route.queryParams.subscribe(params =>{
        if(params['name']){
          this.exercise.username=params['name']
        }
      });
    }

    saveExercise():void{
      (this.exercise.username);
      
      this.exerciseService.saveExercise(this.exercise).subscribe(
        (response) => {
        
          this.showSnackBar('Excercise Added successfully!');
        },
        (error) => {
          console.error('Error saving exercise:', error);
          this.showSnackBar('Error while adding the Excercise');
        }
      );
    }
    showSnackBar(message: string) {
      this.snackBar.open(message, 'Close', {
        duration: 3000, // Duration in milliseconds
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