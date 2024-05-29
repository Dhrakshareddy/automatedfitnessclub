// src/app/user/user.service.ts
import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Models/trainer-user.model';




@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit {
  private apiUrl = 'http://localhost:8082/api/user';
  getUserById: any;

  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

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
  
}

