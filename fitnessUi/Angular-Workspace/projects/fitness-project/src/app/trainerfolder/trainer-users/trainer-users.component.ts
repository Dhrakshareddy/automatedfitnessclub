// src/app/user/user-list/user-list.component.ts
import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../../Models/user.model';
import { UserService } from '../../../Services/user.service';
import { Router } from '@angular/router';
import { TrainerService } from '../../Admin/service/trainer.service';
import { Trainer } from '../../Admin/model/trainer.model';
import { Subscription } from 'rxjs';




@Component({
  selector: 'app-trainer-users',
  templateUrl: './trainer-users.component.html',
  styleUrls: ['./trainer-users.component.css']
})
export class TrainerUsersComponent implements OnInit,OnDestroy {
 
  private subscription!:Subscription
  users: User[] = [];
  public trainer :Trainer=new Trainer(0,'','','',0,'','','');
  trainerCode:string=''

  constructor(private userService: UserService,private trainerService: TrainerService,private router:Router) {
    var getTrainer=localStorage.getItem("trainer");
      
    if(getTrainer)
    {
    this.trainer=JSON.parse(getTrainer);
    this.trainerCode= this.trainer.trainerCode;
   
  
    }
    else{
      console.error("trainer not found");
    }

  }

  ngOnInit(): void {
    this.userService.getUsersByTrainerCode(this.trainerCode).subscribe((users: User[]) => {
     
      (users);
      this.users=users

    });
    
  }

  getAll()
  {
    this.userService.getUsers().subscribe(users =>{
      this.users=users
    })
  }
  
  deleteUser(id: number): void {
   
    
    this.userService.deleteUser(id).subscribe((res) => {
      
    });
  }
  
  goToProgress(name:string)
  {
    this.router.navigate(["trainer/user/progress"] ,{queryParams: {name:name}});
  }

  goToExercise(name:string)
  {
    this.router.navigate(["trainer/user/exercise"] ,{queryParams: {name:name}});
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}