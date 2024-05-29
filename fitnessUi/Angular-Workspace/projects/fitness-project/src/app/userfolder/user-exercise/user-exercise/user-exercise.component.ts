import { Component, OnDestroy, OnInit } from '@angular/core';
import { Exercise } from './userexercise.model';
import { ExerciseService } from './userexercise.service';
import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-user-exercise',
  templateUrl: './user-exercise.component.html',
  styleUrl: './user-exercise.component.css'
})
export class UserExerciseComponent implements OnInit,OnDestroy{

  private subscription!:Subscription
  userName: string = ''; 
  exercise: Exercise[] = [];
  constructor(private exerciseService: ExerciseService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().user.name;
    this.fetchExerciseByName();
  }

  fetchExerciseByName() {

    this.exerciseService.getExerciseByName(this.userName).subscribe((res)=>{
      this.exercise=res;
     
    })
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}



