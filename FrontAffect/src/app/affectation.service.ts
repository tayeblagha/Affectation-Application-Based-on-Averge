import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Affectation } from './affectation';

@Injectable({
  providedIn: 'root'
})
export class AffectationService {
  private baseURL = "http://localhost:8080/api/v2";

  constructor(private httpClient: HttpClient) { }
  getAffectationsList():Observable<Affectation[]>{
    return this.httpClient.get<Affectation[]>(`${this.baseURL}`+"/AffectationList");
  }
}
