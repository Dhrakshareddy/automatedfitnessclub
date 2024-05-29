import { Component, OnDestroy, OnInit } from '@angular/core';

import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { userclassname } from '../../../../Models/userclassname.model';
import { userclassnameservice } from '../../../../Services/userclassnameservice.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-user-class-name',
  templateUrl: './user-class-name.component.html',
  styleUrl: './user-class-name.component.css'
})
export class UserClassNameComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  userName: string = ''; 
  classList: userclassname[] = [];

  constructor(private userService: userclassnameservice,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().user.name;
    this.fetchClasses();
  }

  fetchClasses() {
    this.userService.getClassesByName(this.userName)
    
      .subscribe((data: userclassname[]) => {
        
        this.classList = data;
      });
      console.log(this.classList);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }  
 
}
