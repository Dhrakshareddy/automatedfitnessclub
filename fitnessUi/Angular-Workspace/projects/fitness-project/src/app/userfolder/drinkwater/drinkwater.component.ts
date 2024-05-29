import { Component } from '@angular/core';

@Component({
  selector: 'app-drinkwater',
  templateUrl: './drinkwater.component.html',
  styleUrls: ['./drinkwater.component.css']
})
export class DrinkwaterComponent {
  waterIntake: number = 0;
  dailyGoal: number = 8;

  constructor() {}

  logWater() {
    if (this.waterIntake < this.dailyGoal) {
      this.showNotification('Remember to drink at least 8 glasses of water!');
    } else {
      this.showNotification('Good job! You reached your daily goal.');
    }
  }

  private showNotification(message: string): void {
    alert(message); // Use alert for testing
  }
}
