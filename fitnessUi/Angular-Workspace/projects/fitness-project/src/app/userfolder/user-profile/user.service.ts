// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.model';




@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private apiUrl = 'http://localhost:8082/api/user';
  private doctorapiUrl = 'http://localhost:8082/api/doctor_profile';
  private trainerapiUrl = 'http://localhost:8082/api/trainer';
  private adminapiUrl = 'http://localhost:8082/api/admin_profile';
 
  getUserById: any;

  constructor(private http: HttpClient) {}

   getUsers(): Observable<User[]> {
     return this.http.get<any[]>(`${this.apiUrl}/getAll`);
   }

  //  getUserById(id: number): Observable<User> {
  //    return this.http.get<User>(`${this.apiUrl}/get/${id}`);
  // }

  saveUser(user: User): Observable<User> {
    (user);
    return this.http.post<User>(`${this.apiUrl}/save`, user);
  }

  deleteUser(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }

  loginDetails(details: any): Observable<any> {
    // Assuming your API returns an observable
    return this.http.post<any>(`${this.apiUrl}/login`, details);
  }

  doctorLoginDetails(details: any): Observable<any> {
    // Assuming your API returns an observable
    return this.http.post<any>(`${this.doctorapiUrl}/login`, details);
  }

  trainerLoginDetails(details: any): Observable<any> {
    // Assuming your API returns an observable
    return this.http.post<any>(`${this.trainerapiUrl}/login`, details);
  }
  adminLogin(details: any): Observable<any> {
    return this.http.post<any>(`${this.adminapiUrl}/login`, details);
  }
}

