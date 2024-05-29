// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exercise } from './userexercise.model';
@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
 
  private baseUrl="http://localhost:8085/exercise";
  
  constructor(private http:HttpClient){}

    saveExercise(exercise :Exercise):Observable<Exercise>{
        return this.http.post<Exercise>(`${this.baseUrl}/save`,exercise)
    }

    getExercise(): Observable<Exercise[]> {
      return this.http.get<any[]>(`${this.baseUrl}/getAll`);
    }

    getExerciseByName(username: string): Observable<Exercise[]> {
      (username);
      // const url = `${this.baseUrl}/fetchbyName/${username}`; // Assuming tr API endpoint for fetching workouts by name is '/workouts'
      return this.http.get<Exercise[]>(`${this.baseUrl}/fetchbyName/${username}`);
    }
}

