import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Car } from 'src/app/common/car';
import { CarsService } from 'src/app/service/cars.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent {

  cars: Car[]= [];

  constructor(private carservice: CarsService,private route: ActivatedRoute){}

  ngOnInit(): void {
this.route.paramMap.subscribe(()=>{this.getCars()})  }

  getCars(){
    this.carservice.getCars().subscribe(data => this.cars = data);
    console.log(this.cars);
  }
}
