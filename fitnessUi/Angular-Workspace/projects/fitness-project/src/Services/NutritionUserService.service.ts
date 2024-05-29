import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { NutritionUser } from '../Models/NutritionUser.model';


@Injectable({
  providedIn: 'root'
})
export class NutritionUserService {
  private baseUrl = 'http://localhost:8086/nutrition'; 

  constructor(private http: HttpClient) { }

  getNutritionByName(username: string): Observable<NutritionUser> {
   
    return this.http.get<NutritionUser[]>(`${this.baseUrl}/byName/${username}`)
      .pipe(
       
        map((nutritionUsers: NutritionUser[]) => nutritionUsers[0])
      );
  }
}