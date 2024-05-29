import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NutritionRepo } from '../../../Services/nutrition.repo';
import { Nutrition } from '../../../Models/nutrition.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-nutrition-table',
  templateUrl: './nutrition-table.component.html',
  styleUrls: ['./nutrition-table.component.css']
})
export class NutritionTableComponent implements OnInit,OnDestroy {
  nutrition: Nutrition = {
    username:'',
    foodItem: '',
    caloriesToBeconsumed: '',
    proteinToBeconsumed: '',
    carbohydratesToBeconsumed: '',
    fatsToBeconsumed: '',
    fiberToBeconsumed: '',
    vitaminsAndMineralsToBeconsumed: '',
    sugarToBeconsumed: '',
    sodiumToBeconsumed: ''
  };

  private subscription!:Subscription
  medicalHistory: any;
  userName: string = '';
  users: any[] = []; 

  constructor(
    private nutritionRepo: NutritionRepo,
    private route: ActivatedRoute,
    private router: Router 
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.nutrition.username = params['user'];
    });
  }

  getAllUsers() {
    this.nutritionRepo.getAllUsers()
      .subscribe(users => {
        this.users = users;
      });
  }

  saveNutrition(nutrition: Nutrition) {
    (nutrition);

    console.log("hiiii")
    this.nutritionRepo.saveNutrition(nutrition).subscribe((res)=>{
      
    });
    this.router.navigate(['/doctorDashboard/userfetch']);
  }

  getUserMedicalHistory(userName: string) {
    this.nutritionRepo.getUserMedicalHistory(userName)
      .subscribe(
        (data) => {
          this.medicalHistory = data;
        },
        (error) => {
          console.error("Error fetching medical history:", error);
        }
      );
  }

  
  goBack() {
    this.router.navigate(['/doctorDashboard/userfetch']);
  }
  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}