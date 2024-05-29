// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trainer } from '../model/trainer.model';



@Injectable({
  providedIn: 'root'
})
export class TrainerService {



  private apiUrl = 'http://localhost:8082/api/trainer';


  constructor(private http: HttpClient) { }

  getTrainers(): Observable<Trainer[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getAll`);
  }

  getTrainerById(id: number): Observable<Trainer> {
    return this.http.get<Trainer>(`${this.apiUrl}/getById/${id}`);
  }

  saveTrainer(trainer: Trainer): Observable<Trainer> {
    
    return this.http.post<Trainer>(`${this.apiUrl}/add`, trainer);
  }

  deleteTrainer(id: number): Observable<string> {

    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }

  updatetrainer(trainer: Trainer): Observable<Trainer> {

    return this.http.put<Trainer>(`${this.apiUrl}/updateById`, trainer);
  }

}

