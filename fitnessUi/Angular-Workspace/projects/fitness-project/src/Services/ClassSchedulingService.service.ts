import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClassScheduling } from '../Models/class-scheduling.model';





@Injectable({
  providedIn: 'root'
})
export class ClassSchedulingService {
  private baseUrl = 'http://localhost:8083/classSchedular'; 

  constructor(private http: HttpClient) { }

  getOneWeekClassScheduling(): Observable<ClassScheduling[]> {
    return this.http.get<ClassScheduling[]>(`${this.baseUrl}/weekly`);
  }

  saveClassScheduling(classScheduling: ClassScheduling): Observable<ClassScheduling> {
    if (classScheduling.classId) {
     
      return this.http.put<ClassScheduling>(`${this.baseUrl}/updateById/${classScheduling.classId}`, classScheduling);
    } else {
      
      return this.http.post<ClassScheduling>(`${this.baseUrl}/save`, classScheduling);
    }
  }

  deleteClassScheduling(classId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteById/${classId}`);
  }
}
