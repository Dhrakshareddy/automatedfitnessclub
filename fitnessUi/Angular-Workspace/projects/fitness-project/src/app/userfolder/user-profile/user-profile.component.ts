import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../../Models/user.model';
import { UserService } from '../../Admin/service/user.service';



@Component({
  selector: 'app-user-profile',
  
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent  {
  
  public user :User=new User(0,'','',new Date(),'',0,'','','','');

  constructor(private userService: UserService) {

    var getUser=localStorage.getItem("user");
    if(getUser)
    {

     this.user=JSON.parse(getUser).user;
    console.log(this.user);
  
    }
    else{
      console.error("user not found");
    }

  }
  
        
}

