
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Models/user.model';
import { Nutrition } from '../Models/nutrition.model';




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

  getUsersByTrainerCode(trainerCode:string) {
    return this.http.get<any[]>(`${this.apiUrl}/getByTrainerCode/${trainerCode}`);
  }

   getUsers(): Observable<User[]> {
     return this.http.get<any[]>(`${this.apiUrl}/getAll`);
   }


  saveUser(user: User): Observable<User> {
    (user);
    return this.http.post<User>(`${this.apiUrl}/save`, user);
  }

  deleteUser(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }

  loginDetails(details: any): Observable<any> {
   
    return this.http.post<any>(`${this.apiUrl}/login`, details);
  }

  doctorLoginDetails(details: any): Observable<any> {
    
    return this.http.post<any>(`${this.doctorapiUrl}/login`, details);
  }

  trainerLoginDetails(details: any): Observable<any> {
   
    return this.http.post<any>(`${this.trainerapiUrl}/login`, details);
  }
  adminLoginDetails(details: any): Observable<any> {
   
    return this.http.post<any>(`${this.adminapiUrl}/login`, details);
  }
  
  
}

