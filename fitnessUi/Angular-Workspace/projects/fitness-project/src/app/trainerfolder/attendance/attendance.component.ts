import { Component, OnDestroy, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';
import { Attendance } from '../../../Models/attendance.model';
import { AttendanceService } from '../../../Services/attendance.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css'] 
})

export class AttendanceComponent  implements OnInit,OnDestroy  {
  

  private subscription!:Subscription
  attendance: Attendance = {
    attendanceId: 0,
    name: '',
    date: new Date(),
    status: '',
    userId: 0,
    feedback: '',
   
  };

  constructor(private route: ActivatedRoute,private attendanceService: AttendanceService,private router:Router, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['name']) {
        this.attendance.name = params['name'];
      }
    });
    
  }

  saveAttendance(): void {
   
    this.attendanceService.saveAttendance(this.attendance).subscribe(
      (response) => {
    
        this.showSnackBar('Attendance saved successfully!');
      },
      (error) => {
        console.error('Error saving attendance:', error);
      }
    );
  }
  

  updateAttendance(): void {
    this.attendanceService.updateAttendance(this.attendance).subscribe(
      (response) => {
       
      },
      (error) => {
        console.error('Error updating attendance:', error);
      }
    );
  }
  
  goToAttendance(){
    this.router.navigate(['/user-class']);
  }

  showSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}