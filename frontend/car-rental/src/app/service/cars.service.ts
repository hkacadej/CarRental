import { Car } from './../common/car';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, map } from 'rxjs';
import { CarCategory } from '../common/car-category';

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
}



