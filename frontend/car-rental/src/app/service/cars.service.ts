import { Car } from './../common/car';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private baseUrl = 'http://localhost:8080/cars'

  constructor(private httpClient: HttpClient) { }

  getCars(): Observable<Car[]> {
    console.log();

    return this.httpClient.get<Car[]>(this.baseUrl);
  }

}


