import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NutritionService {
  nutritionData: any = {};

  constructor() { }

  setNutritionData(data: any) {
    this.nutritionData = data;
  }

  getNutritionData() {
    return this.nutritionData;
  }
}