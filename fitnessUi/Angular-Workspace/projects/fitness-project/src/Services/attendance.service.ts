import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attendance } from '../Models/attendance.model';



@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
 
 
  private baseUrl = 'http://localhost:8083/attendance';

  constructor(private http: HttpClient) { }

  
  
  saveAttendance(attendance: Attendance): Observable<Attendance> {
    (attendance);
    return this.http.post<Attendance>(`${this.baseUrl}/save`, attendance);
  }

  updateAttendance(attendance: Attendance): Observable<Attendance> {
    const url = `${this.baseUrl}/updateById/${attendance.attendanceId}`;
    return this.http.put<Attendance>(url, attendance);
  }
  
 
}