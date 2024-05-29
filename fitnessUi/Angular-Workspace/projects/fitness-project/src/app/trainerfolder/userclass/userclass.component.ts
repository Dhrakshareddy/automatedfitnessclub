import { Component, OnDestroy, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { User } from '../../../Models/user.model';
import { UserService } from '../../../Services/user.service';
import { Trainer } from '../../Admin/model/trainer.model';
import { TrainerService } from '../../Admin/service/trainer.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-userclass',
  templateUrl: './userclass.component.html',
  styleUrl: './userclass.component.css'
})
export class UserclassComponent implements OnInit,OnDestroy {

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
    (this.trainer);
  
    }
    else{
      console.error("trainer not found");
    }

  }
  ngOnInit(): void {
    this.userService.getUsersByTrainerCode(this.trainerCode).subscribe((users: User[]) => {
     
      
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
 
  goToAttendance(name: string) {
    this.router.navigate(['trainer/user-class/attendance'], { queryParams: { name: name } });
  }
  goToClasses(name: string) {
    this.router.navigate(['trainer/user-class/classes'], { queryParams: { name: name } });
  }

ngOnDestroy(): void {
  if (this.subscription) {
    this.subscription.unsubscribe();
  }
}

}