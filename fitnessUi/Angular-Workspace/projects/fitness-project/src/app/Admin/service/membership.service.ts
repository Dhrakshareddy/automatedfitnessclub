


import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Membership } from '../model/membership.model';


@Injectable({
  providedIn: 'root',
})
export class MembershipService {

  private baseUrl = 'http://localhost:8082/api/membership';

  constructor(private http: HttpClient) { }

  saveMembership(membership: Membership): Observable<Membership> {


    return this.http.post<Membership>(`${this.baseUrl}/add`, membership);

  }

  getMembership(): Observable<Membership[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getAll`);
  }

  deleteMembership(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/deleteById/${id}`, { responseType: 'text' });
  }

  getMembershipById(id: number): Observable<Membership> {
    return this.http.get<Membership>(`${this.baseUrl}/getById/${id}`);
  }
  updatemembership(membership: Membership): Observable<Membership> {
    return this.http.put<Membership>(`${this.baseUrl}/updateById`, membership)
  }
}




