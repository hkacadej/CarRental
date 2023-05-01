import { Reservation } from './../common/reservation';
import { Car } from './../common/car';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, map } from 'rxjs';
import { CarCategory } from '../common/car-category';
import { Reservate } from '../common/reservate';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private baseUrl = 'http://localhost:8080/cars';
  private categoryUrl = 'http://localhost:8080/categories';

  constructor(private httpClient: HttpClient) { }

  getCars(categoryId: number): Observable<Car[]> {
    const searchUrl = `${this.baseUrl}/category/${categoryId}`
    console.log(searchUrl);
    return this.httpClient.get<Car[]>(searchUrl);
  }
  getCarCategories(): Observable<CarCategory[]>{
    return this.httpClient.get<CarCategory[]>(this.categoryUrl);
  }

  getCarById(carId: number): Observable<Car>{
    const searchUrl = `${this.baseUrl}/${carId}`
    return this.httpClient.get<Car>(searchUrl);
  }
  searchCars(keyWord: string) {
    const searchUrl = `${this.baseUrl}/search/${keyWord}`
    console.log(searchUrl);
    return this.httpClient.get<Car[]>(searchUrl);
  }

  getReservations(carId : number): Observable<Reservation[]>{
    return this.httpClient.get<Reservation[]>(`http://localhost:8080/reservations/${carId}`);
  }

  makeReservation(reservate : Reservate): Observable<any>{
    console.log(reservate);
    return this.httpClient.post<Reservate>('http://localhost:8080/reservations/reserve',reservate)
      .pipe(
        map(response => {
          console.log(response);
          return response;
        })
      );
  }

}



