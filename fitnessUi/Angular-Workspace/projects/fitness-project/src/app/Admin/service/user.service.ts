// src/app/user/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../../Models/user.model';
import { Nutrition } from '../../../Models/nutrition.model';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  updateNutrition(nutrition: Nutrition) {
    throw new Error('Method not implemented.');
  }

  private apiUrl = 'http://localhost:8082/api/user';

  updatePassword(email: any, password: any) {
    return this.http.put<User>(`${this.apiUrl}/updatepassword?email=${email}&password=${password}`, {});
  }
  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getAll`);
  }

  getUserById(id: number): Observable<User> {
    
    return this.http.get<User>(`${this.apiUrl}/getById/${id}`);
  }

  saveUser(user: User): Observable<User> {
    
    return this.http.post<User>(`${this.apiUrl}/save`, user);
  }

  deleteUser(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deleteById/${id}`, { responseType: 'text' });
  }
  updateuser(user: User): Observable<User> {

    return this.http.put<User>(`${this.apiUrl}/updateById`, user);
  }

}

