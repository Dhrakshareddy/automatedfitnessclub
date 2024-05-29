import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Payments } from "./payment.model";

export class PaymentService{
    private baseUrl = 'http://localhost:8084/payment';

    constructor(private http: HttpClient) { }
  
  makePayment(paymentData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/save`, paymentData);   
  }
  getUserByName(username: string): Observable<Payments[]> {
    
    return this.http.get<Payments[]>(`${this.baseUrl}/fetchbyName/${username}`);
  }
}