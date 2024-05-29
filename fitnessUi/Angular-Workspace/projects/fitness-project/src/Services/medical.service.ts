// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MedicalHistory } from '../Models/medical-history.model';






@Injectable({
  providedIn: 'root'
})
export class MedicalService {
 
  private apiUrl = 'http://localhost:8086/medicalHistory';
  getMedicalById: any;
  findByName: any;

  constructor(private http: HttpClient) {}

   getMedicalHistory(): Observable<MedicalHistory[]> {
     return this.http.get<any[]>(`${this.apiUrl}/getAll`);
   }

 

  saveMedicalHistory(medicalHistory: MedicalHistory): Observable<MedicalHistory> {
   
    return this.http.post<MedicalHistory>(`${this.apiUrl}/save`, medicalHistory);
  }
}