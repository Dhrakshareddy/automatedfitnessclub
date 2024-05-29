// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Workout } from '../Models/workout.model';





@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
 
  private baseUrl="http://localhost:8085/workout";
  
  constructor(private http:HttpClient){}

    saveWorkout(workout : Workout):Observable<Workout>{
      
        return this.http.post<Workout>(`${this.baseUrl}/save`,workout)
    }

    getWorkout(): Observable<Workout[]> {
      return this.http.get<any[]>(`${this.baseUrl}/getAll`);
    }

   
    getWorkoutsByName(username: string): Observable<Workout[]> {

      return this.http.get<Workout[]>(`${this.baseUrl}/fetchbyName/${username}`);
    }

   
 
}

