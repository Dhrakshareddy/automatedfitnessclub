
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Models/user.classmodel';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
 
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`http://localhost:8082/api/user/getById/${userId}`);
  }

 

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8082/api/user/getAll');
  }

 
}