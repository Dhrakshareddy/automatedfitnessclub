import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Trainer } from "../Models/trainer.model";


@Injectable({
    providedIn: 'root'
  })
  export class TrainerService {

    private apiUrl = 'http://localhost:8082/api/trainer/getById'; // Assuming your backend API URL

  constructor(private http: HttpClient) { }

  getTrainerById(id: number): Observable<Trainer> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Trainer>(url);
  }


  }