import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MedicalHistoryServiceDisplayModel } from '../Models/medical-history-display.model';
import { MedicalHistory } from '../Models/medical-history.model';





@Injectable({
  providedIn: 'root'
})
export class MedicalHistoryDisplayClassService {
  private baseUrl = 'http://localhost:8086/medicalHistory'; 

  constructor(private http: HttpClient) { }


getMedicalHistory(username: string): Observable<MedicalHistory[]> {
  
  return this.http.get<MedicalHistory[]>(`${this.baseUrl}/fetchbyName/${username}`);
}

}