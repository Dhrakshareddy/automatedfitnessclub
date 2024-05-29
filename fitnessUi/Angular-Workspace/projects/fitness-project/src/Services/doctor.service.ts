// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Doctor } from '../Models/doctor.model';




@Injectable({
  providedIn: 'root'
})
export class DoctorService {
 
  

  private apiUrl = 'http://localhost:8082/api/doctor_profile';
  

  constructor(private http: HttpClient) {}

   getDoctors(): Observable<Doctor[]> {
     return this.http.get<any[]>(`${this.apiUrl}/getAll`);
   }

    getDoctorById(id: number): Observable<Doctor> {
     return this.http.get<Doctor>(`${this.apiUrl}/get/${id}`);
  }

  saveDoctor(doctor: Doctor): Observable<Doctor> {
    (doctor);
    return this.http.post<Doctor>(`${this.apiUrl}/add`, doctor);
  }

  deleteDoctor(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }

  fetchDoctorByCode(doctorCode:String){
    return this.http.get<Doctor>(`${this.apiUrl}/getByCode/${doctorCode}`)
  }
  
}

