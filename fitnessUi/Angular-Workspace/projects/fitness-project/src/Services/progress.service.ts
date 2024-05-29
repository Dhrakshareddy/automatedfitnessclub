// src/app/user/user.service.ts
import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


import { Observable, catchError, throwError } from 'rxjs';
import { CalculationRequest } from '../app/trainerfolder/progress/calculationrequest';
import { Progress } from '../Models/progress.model';
import { Exercise } from '../Models/exercise.model';
import { Workout } from '../Models/workout.model';




@Injectable({
  providedIn: 'root'
})
export class ProgressService implements OnInit {
  private apiUrl = 'http://localhost:8085/progress';
  getProgressById: any;

  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  calculateProgress(request: CalculationRequest): Observable<number> {
    const url = `${this.apiUrl}/calculate`;
    return this.http.post<number>(url, request).pipe(
      catchError((error) => {
        console.error('Error calculating progress:', error);
        return throwError(error); 
      })
    );
  }
  

   getProgresses(): Observable<Progress[]> {
     return this.http.get<any[]>(`${this.apiUrl}/getAll`);
   }

  saveProgress(progress: Progress): Observable<Progress> {
    (progress);
    return this.http.post<Progress>(`${this.apiUrl}/save`, progress);
  }

  getProgressByName(username: string): Observable<Progress[]> {
    
    
    return this.http.get<Progress[]>(`${this.apiUrl}/fetchbyName/${username}`);
  }

 

  getExerciseByName(name: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(`${this.apiUrl}/forexercisebyname/${name}`);
  }
  getWorkoutByName(name: string): Observable<Workout[]> {
    return this.http.get<Workout[]>(`${this.apiUrl}/forworkoutbyname/${name}`);
  }

  deleteProgress(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }
  
}