import { Component, OnDestroy, OnInit } from '@angular/core';
import { Progress } from '../../../../Models/progress.model';
import { ProgressService } from '../../../../Services/progress.service';
import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-progress-reports',
  
  templateUrl: './progress-reports.component.html',
  styleUrl: './progress-reports.component.css'
})
export class ProgressReportsComponent implements OnInit,OnDestroy
{

  private subscription!:Subscription
  userName: string = ''; 
  progress: Progress[] = [];
  constructor(private progressService: ProgressService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().user.name;
  
    this.fetchProgressByName();
  }
  fetchProgressByName() {

    this.progressService.getProgressByName(this.userName).subscribe((res)=>{

      this.progress=res;
      
    })
  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}