import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 


import { Observable } from 'rxjs';
import { Nutrition } from '../Models/nutrition.model';

@Injectable({ providedIn: 'root' })
export class NutritionRepo {
  public nutrition: Nutrition[] = [];
  private baseUrl = 'http://localhost:8086/nutrition';
  constructor(
    
    private http: HttpClient // Inject HttpClient
  ) {}

 

  saveNutrition(nutrition: Nutrition): Observable<Nutrition> {

    console.log("hloo");
    console.log(nutrition);
    return this.http.post<Nutrition>(`${this.baseUrl}/save`, nutrition);
  }


  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8086/nutrition/getUsers');
  }


  getUserMedicalHistory(username: string): Observable<any> {
    const url = `http://localhost:8086/medicalHistory/fetchbyName/${username} `;
    return this.http.get<any>(url);
  }


  updateNutrition(nutrition: Nutrition): Observable<any> {
    const url = 'http://localhost:8086/nutrition/updateById';
    return this.http.put<any>(url, nutrition);
  }
  
}