import { Component, OnDestroy, OnInit } from '@angular/core';



import { ActivatedRoute, Router } from '@angular/router';
import { MedicalService } from '../../../Services/medical.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MedicalHistory } from '../../../Models/medical-history.model';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-medical-history',
  templateUrl: './medical-history.component.html',
  styleUrls: ['./medical-history.component.css']
})

  export class MedicalHistoryComponent  implements OnInit,OnDestroy  {
  

  user: any;
  getUserName: any;
  private subscription!:Subscription
  
  medicalHistory: MedicalHistory = {
    username: '',
    dateOfAssessment: '',
    heartRate: 0,
    allergies: '',

    previousInjuryOrSurgery: '',
    fitnessLevel: 0,
    bodyMassIndex: 0,
    bloodPressure: '',
    createdBy: undefined,
    medicalHistoryId: 0
  };
    constructor(private route: ActivatedRoute,private MedicalService: MedicalService,private router:Router, private snackBar: MatSnackBar) {}

    ngOnInit(): void {
      this.getUserName=localStorage.getItem("user");
      if(this.getUserName)
      {
      this.getUserName=JSON.parse(this.getUserName);
      this.getUserName.name;
      this.medicalHistory.username = this.getUserName.name;
      }
      else{
        ("trainer not found");
      }
    }
  
    saveMedicalHistory(): void {
     
      this.MedicalService.saveMedicalHistory(this.medicalHistory).subscribe(
        (response) => {
          
          this.showSnackBar('Medical History saved successfully!');
          this.router.navigate(['/']);
        },
        (error) => {
          console.error('Error saving attendance:', error);
        }
      );
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