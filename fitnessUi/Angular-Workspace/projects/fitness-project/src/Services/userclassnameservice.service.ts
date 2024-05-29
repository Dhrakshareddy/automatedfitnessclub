import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { userclassname } from '../Models/userclassname.model';




@Injectable({
  providedIn: 'root'
})
export class userclassnameservice {
  private baseUrl = 'http://localhost:8083/classSchedular'; 

  constructor(private http: HttpClient) { }

 

  getClassesByName(name: string): Observable<userclassname[]> {
    return this.http.get<userclassname[]>(`${this.baseUrl}/byName/${name}`);
  }
}