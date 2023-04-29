import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Car } from 'src/app/common/car';
import { CarsService } from 'src/app/service/cars.service';

@Component({
  selector: 'app-cars-detail',
  templateUrl: './cars-detail.component.html',
  styleUrls: ['./cars-detail.component.css']
})
export class CarsDetailComponent {
  car!: Car;
  categoryId!: number;

  constructor(private carService: CarsService,
              private route: ActivatedRoute){
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.handleCarDetails();
    })
  }
  handleCarDetails(): void {

    //get the id param and convert to number
    const carId = +this.route.snapshot.paramMap.get('id')!;
    this.carService.getCarById(carId).subscribe(
      data => {
        this.car=data;
      }
    )
  }

  reserve(car: Car) {
    console.log(car);
    }
}
