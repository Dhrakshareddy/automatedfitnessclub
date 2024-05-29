import { Component, OnDestroy, OnInit } from '@angular/core';

import { WorkoutService } from '../../../Services/workout.service';
import { Workout } from '../../../Models/workout.model';
import { AuthService } from '../../Dashboard/dash-board/auth.service';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-workout-list',
  templateUrl: './workout-list.component.html',
  styleUrl: './workout-list.component.css'
})
export class WorkoutListComponent  implements OnInit,OnDestroy{

  private subscription!:Subscription
  userName: string = ''; 
  workout: Workout[] = [];
  constructor(private workoutService: WorkoutService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().user.name;
    
    this.getWorkout();
  }
  getWorkout() {
  (this.userName)
    this.workoutService.getWorkoutsByName(this.userName).subscribe((res)=>{

      this.workout=res;
      
    })
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}

