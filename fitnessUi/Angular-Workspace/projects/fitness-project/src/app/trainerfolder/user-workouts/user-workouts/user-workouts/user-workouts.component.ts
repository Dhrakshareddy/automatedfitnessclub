import { Component, OnDestroy, OnInit } from '@angular/core';

import { Subscription } from 'rxjs';


import { AuthService } from '../../../../Dashboard/dash-board/auth.service';

import { WorkoutService } from '../../../../../Services/workout.service';
import { Workout } from '../../../../../Models/workout.model';

@Component({
  selector: 'app-user-workouts',
  templateUrl: './user-workouts.component.html',
  styleUrl: './user-workouts.component.css'
})
export class UserWorkoutsComponent implements OnInit,OnDestroy {

  private subscription: Subscription = new Subscription();
  username: string = ""; 
  workouts: Workout[] = []; 
  userName: any;

  constructor(private workoutService: WorkoutService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().name;
    // alert(this.userName)
    this.fetchWorkoutsByName();
  }

  fetchWorkoutsByName() {
    (this.userName)
    this.workoutService.getWorkoutsByName(this.userName).subscribe((res)=>{

    })
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}


function subscribe(arg0: (data: Workout[]) => void) {
  throw new Error('Function not implemented.');
}