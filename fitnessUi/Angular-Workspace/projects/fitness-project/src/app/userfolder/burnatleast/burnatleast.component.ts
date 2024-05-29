import { Component } from '@angular/core';

@Component({
  selector: 'app-burnatleast',
  templateUrl: './burnatleast.component.html',
  styleUrl: './burnatleast.component.css'
})
export class BurnatleastComponent {
  activity: string = '';
  duration: number = 0;
  name:string="";
  caloriesPerMinute: number = 5; // Change this value according to your calculation

  caloriesBurned: number = 0;

  calculateCalories(): void {
    if (this.activity && this.duration) {
      this.caloriesBurned = this.caloriesPerMinute * this.duration;
    } else {
      this.caloriesBurned = 0;
    }
  }

}
