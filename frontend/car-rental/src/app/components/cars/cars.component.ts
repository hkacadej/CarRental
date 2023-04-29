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
  currentCategoryId: number = 1;
  currentCategoryName: string = "";
  searchMode: boolean = false;
  previousCategoryId: number=1;

  constructor(private carservice: CarsService,private route: ActivatedRoute){}

  ngOnInit(): void {
  this.route.params.subscribe(()=>{this.listCars()})

  }
  listCars() {
    this.searchMode=this.route.snapshot.paramMap.has('keyword');

    if(this.searchMode){
       this.handleSearchCars();
    }else{
      this.handleListCars();
    }
  }

  handleSearchCars() {
    const keyWord: string = this.route.snapshot.paramMap.get('keyword')!;
    this.carservice.searchCars(keyWord).subscribe(data=>{this.cars=data})
  }
  handleListCars() {
    const categoryIdExists: boolean = this.route.snapshot.paramMap.has('id');
    const categoryNameExists: boolean = this.route.snapshot.paramMap.has('name');
    if(categoryIdExists){
      console.log('aaa');
      // get "id" string , convert to number with "+" the "!" at the end tells the compiler that the value is not null
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;

      //get name param string
      this.currentCategoryName = this.route.snapshot.paramMap.get('name')!;
    }
    else{
      console.log(this.route.params);
      //vihet default te 1
      this.currentCategoryId=6;
      this.currentCategoryName="SEDAN";
    }
    this.previousCategoryId = this.currentCategoryId;

    this.getCars()
  }
  proccesResult(): any {
    return(data: any) => {
      this.cars= data;
    }
  }

  getCars(){
    this.carservice.getCars(this.currentCategoryId).subscribe(data => this.cars = data);
    console.log(this.cars);
  }
}
