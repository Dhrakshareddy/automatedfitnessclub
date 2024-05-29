


import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipment } from '../model/equipment.model';


@Injectable({
  providedIn: 'root',
})
export class EquipmentService {

  private baseUrl = 'http://localhost:8082/api/equipment';

  constructor(private http: HttpClient) { }

  saveEquipment(equipment: Equipment): Observable<any> {

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/save`, equipment, { headers });

  }


  getEquipment(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.baseUrl}/getAll`);
  }


  deleteEquipment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteById/${id}`);
  }


  getEquipmentById(id: number): Observable<Equipment> {
    return this.http.get<Equipment>(`${this.baseUrl}/getById/${id}`);
  }

  updateequipment(equipment: Equipment): Observable<Equipment> {

    return this.http.put<Equipment>(`${this.baseUrl}/updateById`, equipment);
  }
}




