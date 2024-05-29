import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../Dashboard/dash-board/auth.service';
import { WorkoutService } from '../../../Services/workout.service';
import { Workout } from '../../../Models/workout.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-fetchworkouts',
  templateUrl: './fetchworkouts.component.html',
  styleUrl: './fetchworkouts.component.css'
})
export class FetchworkoutsComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  workouts: Workout[] = []; 


  constructor(private workoutService: WorkoutService) { }

  ngOnInit(): void {

    this.fetchWorkouts();
  }

  fetchWorkouts() {
    this.workoutService.getWorkout()
      .subscribe((data: Workout[]) => {
        console.log(data); 
        this.workouts = data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}




