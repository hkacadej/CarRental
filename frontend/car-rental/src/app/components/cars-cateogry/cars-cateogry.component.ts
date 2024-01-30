import { AuthService } from './../../authentication/services/auth-service.service';
import { Component } from '@angular/core';
import { CarCategory } from 'src/app/common/car-category';
import { CarsService } from 'src/app/service/cars.service';

@Component({
  selector: 'app-cars-cateogry',
  templateUrl: './cars-cateogry.component.html',
  styleUrls: ['./cars-cateogry.component.css']
})
export class CarsCateogryComponent {

  carCategories: CarCategory[]=[];
  managerPages: String[];
  constructor(private carService: CarsService , private authService : AuthService){}

  ngOnInit() {
    this.listProductCategories();
  }

  listProductCategories() {
    this.carService.getCarCategories().subscribe(
      data => {
        this.carCategories=data;
      }
    );

    this.authService.getManagerPages().subscribe(
      data => this.managerPages=data
    )
  }
}
