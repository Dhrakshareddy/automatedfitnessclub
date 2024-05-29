import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { fetchDoctor } from "../Models/fetchDoctor.model";



export  class DoctorFetchService{
    private baseUrl = 'http://localhost:8082/api/doctor'; 

  constructor(private http: HttpClient) { }
  getDoctorByName(name: string): Observable<fetchDoctor[]> {
    return this.http.get<fetchDoctor[]>(`${this.baseUrl}/name/${name}`);
}
}   