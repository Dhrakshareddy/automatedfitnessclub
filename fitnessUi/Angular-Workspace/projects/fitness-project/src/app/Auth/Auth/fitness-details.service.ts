import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';



@Injectable({
  providedIn: 'root'
})
export class FitnesstypeDetailsService {
  
  
  constructor(private http: HttpClient) { }

  
  private apiUrl = 'http://localhost:8082/api/user';
  private doctorapiUrl = 'http://localhost:8082/api/doctor_profile';
  private trainerapiUrl = 'http://localhost:8082/api/trainer';
  private adminapiUrl = 'http://localhost:8082/api/admin_profile';
  

  
  loginDetails(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).append('No-AUTH', 'True'); // Append authentication headers
    return this.http.post<any[]>(`${this.apiUrl}/login`, data, { headers: headers })
      .pipe(
        tap(data => console.log("login", data)) // Logging the response data
      ); 
  }
  
  doctorLoginDetails(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).append('No-AUTH', 'True'); // Append authentication headers
    return this.http.post<any[]>(`${this.doctorapiUrl}/login`, data, { headers: headers })
      .pipe(
        tap(data => console.log("login", data)) // Logging the response data
      ); 
  }

  adminLoginDetails(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).append('No-AUTH', 'True'); // Append authentication headers
    return this.http.post<any[]>(`${this.adminapiUrl}/login`, data, { headers: headers })
      .pipe(
        tap(data => console.log("login", data)) // Logging the response data
      ); 
  }

  trainerLoginDetails(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }).append('No-AUTH', 'True'); // Append authentication headers
    return this.http.post<any[]>(`${this.trainerapiUrl}/login`, data, { headers: headers })
      .pipe(
        tap(data => console.log("login", data)) // Logging the response data
      ); 
  }
  

}