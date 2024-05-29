import { Component, OnDestroy, OnInit } from '@angular/core';

import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { AttendanceUser } from '../../../../Models/AttendanceUser.model';
import { AttendanceUserService } from '../../../../Services/AttendanceUserService.service';
import { Subscription } from 'rxjs';





@Component({
  selector: 'app-attendance-user',
  templateUrl: './attendance-user.component.html',
  styleUrl: './attendance-user.component.css'
})
export class AttendanceUserComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  userName: string = ''; 
  attendanceList: AttendanceUser[] = [];

  constructor(private attendanceService: AttendanceUserService,private auth:AuthService) { }


  ngOnInit(): void {
    this.userName=this.auth.getUser().user.name;

    (this.userName);
  
    this.fetchAttendance();
  }

  fetchAttendance() {
    this.attendanceService.getAttendanceByUserName(this.userName)
      .subscribe((data) => {
      
        this.attendanceList = data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  
}