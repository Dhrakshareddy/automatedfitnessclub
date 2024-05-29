import { Component, OnDestroy, OnInit } from '@angular/core';

import { AuthService } from '../../Dashboard/dash-board/auth.service';
import { NutritionUser } from '../../../Models/NutritionUser.model';
import { NutritionUserService } from '../../../Services/NutritionUserService.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-fetchnutrition',
  templateUrl: './fetchnutrition.component.html',
  styleUrls: ['./fetchnutrition.component.css']
})
export class FetchnutritionComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  username: string = '';
  nutritionList: NutritionUser[] = [];

  constructor(private nutritionService: NutritionUserService,private auth:AuthService) { }

  ngOnInit(): void {
    
    
    this.username=this.auth.getUser().user.name;
    console.log(this.username);
    this.fetchNutrition();
  }

  fetchNutrition() {
   
    this.nutritionService.getNutritionByName(this.username)
      .subscribe((data: NutritionUser) => {
       
       
        this.nutritionList = [data];
      });
  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}