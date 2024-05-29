import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from './payment.model';

@Injectable({
  providedIn: 'root'
})
export class plansservice {
  private baseUrl = 'http://localhost:8084/payment';
  username: any;

  constructor(private http: HttpClient) { }

makePayment(paymentData: Payment): Observable<Payment> {
  return this.http.post<Payment>(`${this.baseUrl}/save`, paymentData);   }
}