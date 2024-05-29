import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AttendanceUser } from '../Models/AttendanceUser.model';



@Injectable({
  providedIn: 'root'
})
export class AttendanceUserService {
  private baseUrl = 'http://localhost:8082/api/user'; 

  constructor(private http: HttpClient) { }

  getAttendanceByUserName(username: string): Observable<AttendanceUser[]> {

   
    return this.http.get<AttendanceUser[]>(`http://localhost:8083/attendance/byName/${username}`);
  }

  getUsers(): Observable<AttendanceUser[]> {
    return this.http.get<AttendanceUser[]>(`${this.baseUrl}/getAll`);
  }
  fetchUserAttendanceDetails(userId: number) {
    return this.http.get(`${this.baseUrl}/getByName`);
  }
 
}
