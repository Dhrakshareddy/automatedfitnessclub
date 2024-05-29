import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { fetchTrainer } from "../Models/fetchTrainer.model";



export  class TrainerFetchService{
    private baseUrl = 'http://localhost:8082/api/trainer'; 

  constructor(private http: HttpClient) { }
  getTrainerByName(name: string): Observable<fetchTrainer[]> {
    return this.http.get<fetchTrainer[]>(`${this.baseUrl}/name/${name}`);
}
}   