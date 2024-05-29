// src/app/user/user.service.ts
import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


import { Observable, catchError, throwError } from 'rxjs';
import { Exercise } from '../Models/exercise.model';


@Injectable({
  providedIn: 'root'
})
export class ExerciseService implements OnInit {
  private apiUrl = 'http://localhost:8085/exercise';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getExerciseByName(name: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(`${this.apiUrl}/forexercisebyname/${name}`);
  }
   

  saveExercise(exercise: Exercise): Observable<Exercise> {
    (exercise);
    return this.http.post<Exercise>(`${this.apiUrl}/save`, exercise);
  }

  
  
}

