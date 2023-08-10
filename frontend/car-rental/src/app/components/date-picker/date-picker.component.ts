import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatCalendarCellClassFunction, MatCalendarCellCssClasses, MatDatepickerInputEvent } from '@angular/material/datepicker';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from 'src/app/common/car';
import { Reservate } from 'src/app/common/reservate';
import { Reservation } from 'src/app/common/reservation';
import { CarsService } from 'src/app/service/cars.service';

@Component({
  selector: 'app-date-picker',
  templateUrl: './date-picker.component.html',
  styleUrls: ['./date-picker.component.css']
})


export class DatePickerComponent {
  constructor(private carService: CarsService, private route: ActivatedRoute, private router : Router) { }
  reservations: Reservation[] = [];
  reservedDates: string[] = [];
  reserveFormGroup!: FormGroup;
  isError = false;
  currentCategoryId : string  = '';
  car! : Car ;
  finalprice : number = 0;
  ngOnInit(): void {
    this.route.paramMap.subscribe(
      () => {
        const carId = +this.route.snapshot.paramMap.get('id')!;
        this.currentCategoryId = this.route.snapshot.paramMap.get('catedoryId')!
        this.carService.getReservations(carId).subscribe(data => {
          this.reservations = data;
          this.reservedDates = this.getReservedDates();
          this.carService.getCarById(carId).subscribe(
            data =>{
              this.car = data;
            }
          )
        })
      }
    )

  }

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  getDatesInRange(dateFrom: Date, endDate: Date) {

    const dates = [];
    let currentDate = new Date(dateFrom);
    let dateTo = new Date(endDate);
    currentDate.setHours(0);
    currentDate.setMinutes(0);
    currentDate.setSeconds(0);
    currentDate.setMilliseconds(0);
    dateTo.setHours(0);
    while (currentDate <= dateTo) {
      dates.push(currentDate.toISOString());
      currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // add 1 day
    }
    //console.log(dates);
    return dates;

  }
  dateClass() {
    return (date: Date): MatCalendarCellCssClasses => {

      if (this.reservedDates.includes(date.toISOString())) {
        return 'special-date';
      } else {
        return '';
      }
    };
  }
  getReservedDates() {
    let dates: any[] = [];

    for (let reservation of this.reservations) {
      dates = dates.concat(this.getDatesInRange(reservation.dateFrom, reservation.dateTo));
    }
    return dates;
  }
  validateDates(type: string, event: MatDatepickerInputEvent<Date>){
    if(this.range.get('start')?.value != null && this.range.get('end')?.value != null){
      const dateFrom : Date= this.range.get('start')?.value;
      const dateTo : Date = this.range.get('end')?.value;
      if(this.reservedDates.includes(dateFrom.toISOString()) || this.reservedDates.includes(dateTo.toISOString())){
        this.range.get('start')?.setValue(null);
        this.range.get('end')?.setValue(null);
        this.isError= true;
      }else{
        const dateFrom : Date= this.range.get('start')?.value;
        const dateTo : Date = this.range.get('end')?.value;
        this.finalprice  = this.getDatesInRange(dateFrom , dateTo).length * this.car.price;
        this.isError= false;
      }
    }
  }

  onSubmit(){
    const dateFrom : Date= this.range.get('start')?.value;
    const dateTo : Date = this.range.get('end')?.value;
    this.finalprice  = this.getDatesInRange(dateFrom , dateTo).length * this.car.price;
    const reserve : Reservate = new Reservate(
    dateFrom.toISOString(),
    dateTo.toISOString(),
    this.car);
    console.log(reserve);
    this.carService.makeReservation(reserve).subscribe((data) => {
        if(data){
          this.router.navigate([`/cars/${this.car.id}/${this.currentCategoryId} `]);
        }else{
          this.isError= true;
        }
    });

  }





}
