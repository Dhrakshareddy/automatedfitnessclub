import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';



import { UserService } from '../../../Services/doctorUser.service';
import { Nutrition } from '../../../Models/nutrition.model';
import { NutritionRepo } from '../../../Services/nutrition.repo';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-userfetch',
  templateUrl: './userfetch.component.html',
  styleUrls: ['./userfetch.component.css']
})
export class UserfetchComponent implements OnInit,OnDestroy {
  private subscription!:Subscription
  users!: any[]; // Definite assignment assertion
  medicalHistory: any[] = []; // Initialize medicalHistory array
  medicalHistoryNotFound: boolean = false;
  errorFetchingMedicalHistory: boolean = false;
  nutrition: Nutrition = {
    username: '',
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

  currentDate: string = ''; // Define currentDate variable
  snackBar: any;

  constructor(
    private nutritionRepo: NutritionRepo,
    private userService: UserService,
    private router: Router
  ) {
    this.currentDate = new Date().toLocaleString();
  }
  ngOnInit(): void {
    this.getAll(); // Call method to fetch all users when component initializes
  }

  getAll() {
    this.userService.getAllUsers().subscribe(users => {
      (users);

      this.users = users
    })
  }

  getUserMedicalHistory(name: string) {
    this.medicalHistoryNotFound = false;
    this.errorFetchingMedicalHistory = false;

    this.nutritionRepo.getUserMedicalHistory(name).subscribe(
      data => {

        this.router.navigate(['/doctorDashboard/userfetch/medicalHistoryDisplay'],{ state: { name: name } });

        if (data.length === 0) {
          this.medicalHistoryNotFound = true;
          this.medicalHistory = [];

        } else {
          this.medicalHistoryNotFound = false;
          this.medicalHistory = data;
        }
      },
      error => {
        console.error('Error fetching medical history:', error);
        this.errorFetchingMedicalHistory = true;

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

  goToNutritionTable(username: string) {
    this.router.navigate(['/doctorDashboard/nutrition'], { queryParams: { user: username } });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}