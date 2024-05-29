// auth.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 private apiUrl = 'http://localhost:8082/api/user';

  constructor(private http: HttpClient) {}

  requestPasswordReset(username: string): Observable<any> {
    const resetRequest = {
      username: username
    };

    return this.http.post(`${this.apiUrl}/password-reset-endpoint`, resetRequest);
  }

  // Add other authentication-related methods here
}
