import { Component, OnDestroy, OnInit } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';



import { User } from '../../../Models/user.model';

import { CalculationRequest } from './calculationrequest';
import { Progress } from '../../../Models/progress.model';
import { Exercise } from '../../../Models/exercise.model';
import { Workout } from '../../../Models/workout.model';
import { ProgressService } from '../../../Services/progress.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css']
})
export class ProgressComponent implements OnInit,OnDestroy{

  private subscription!:Subscription
  progressForm: any;
  
  percentages: number = 0;
  progress: Progress = {
    progressId: 0,
    progressDate: new Date,
    calculateProgress: 0,
    username: '',
    createdBy: undefined
  };

  username:string="";
  exercises: Exercise[] = [];
  workouts: Workout[] = [];
  public user: User = new User(0, '', '', new Date(), '', 0, '', '', '','');
  public calculationRequest:CalculationRequest=new CalculationRequest(this.exercises,this.workouts);

  constructor(
    private progressService: ProgressService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private snakBar:MatSnackBar,
  ) { }

  calculateProgress() {
    this.progressService.calculateProgress(this.calculationRequest).subscribe(
      
      (result) => {
        this.percentages = result;
        this.progress.calculateProgress=this.percentages
      },
      (error) => {
        console.error('Error calculating progress:', error);
       
      }
    );
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params =>{
      if(params['name']){
        this.progress.username=params['name'];
        this.username=params['name']
      }
    });

    this.progressForm = this.formBuilder.group({
      progressDate: ['', Validators.required],
      calculateProgress: [''],
    });

    this.user = history.state.user;

    this.progressService.getExerciseByName(this.username).subscribe(
      (exercise) => {
        this.exercises = exercise;
        this.calculationRequest.exercises=this.exercises;
        
  
        this.progressService.getWorkoutByName(this.username).subscribe(
          (workout) => {
            this.workouts = workout;
         
            this.calculationRequest.workouts=this.workouts;
          
  
            
            this.calculateProgress();
          },
          (error) => {
            console.error('Error getting workouts:', error);
          }
        );
      },
      (error) => {
        console.error('Error getting exercises:', error);
      }
    );
  }

 saveProgress(): void {
 
      this.progressService.saveProgress(this.progress).subscribe(
        (response) => {
          this.showSnackBar('Proress added successfully!');
        
        },
        (error) => {
          console.error('Error saving progress:', error);
          this.showSnackBar('Error while saving progress');
        }
      );
    }
    
  showSnackBar(message: string) {
    this.snakBar.open(message, 'Close', {
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
