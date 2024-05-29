import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { MedicalHistory } from "../Models/medical-history.model";



@Injectable({ providedIn: "root" })
export class Datasource {
  constructor(private http: HttpClient) {}

  saveNewMedicalHistory(medicalHistory: MedicalHistory): Observable<MedicalHistory> {
    return this.http.post<MedicalHistory>('http://localhost:8086/medicalHistory/save', medicalHistory);
  }
}
