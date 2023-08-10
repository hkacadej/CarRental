import { CarDto } from './../../common/car-dto';
import { CarsService } from '../../service/cars.service';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Car } from 'src/app/common/car';
import { CarCategory } from 'src/app/common/car-category';
import { CarValidatorService } from 'src/app/validators/car-validator';

@Component({
  selector: 'app-insert',
  templateUrl: './insert.component.html',
  styleUrls: ['./insert.component.css']
})
export class InsertComponent {
  carFormGoup!: FormGroup;
  carCategories!: CarCategory[];
  selectedImage: File | null = null;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private carsService: CarsService) { }

  ngOnInit(): void {
    this.carFormGoup = this.formBuilder.group({
      car: this.formBuilder.group({
        plate: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        make: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        model: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        fuel: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        engine: new FormControl('', [Validators.required,
        Validators.minLength(1)]),


        year: new FormControl('', [Validators.required,
        Validators.minLength(2)]),


        capacity: new FormControl('', [Validators.required,
        Validators.minLength(2)]),


        price: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        category: new FormControl('', Validators.required)
      })
    });

    this.carsService.getCarCategories().subscribe(
      data => {
        console.log(data[0].categoryName)
        this.carCategories = data;
      });

  }

  onImageSelected(event: any): void {
    this.selectedImage = event.target.files[0];
  }

  get plate() { return this.carFormGoup.get('car.plate'); }
  get make() { return this.carFormGoup.get('car.make'); }
  get model() { return this.carFormGoup.get('car.model'); }
  get fuel() { return this.carFormGoup.get('car.fuel'); }
  get engine() { return this.carFormGoup.get('car.engine'); }
  get year() { return this.carFormGoup.get('car.year'); }
  get capacity() { return this.carFormGoup.get('car.capacity'); }
  get price() { return this.carFormGoup.get('car.price'); }
  get category() { return this.carFormGoup.get('car.category'); }


  onSubmit() {

    if (this.carFormGoup.invalid) {
      console.log(true);
      this.carFormGoup.markAllAsTouched();
      return;
    }



    //set up car
    let car = new Car();

    console.log(this.selectedImage);

    car = this.carFormGoup.controls['car'].value;
    car.imageUrl = this.selectedImage.type;
    this.carsService.insertCar(car).subscribe(
      {
        next: carResp => {
          console.log("response")
          console.log(carResp);
         if(this.selectedImage.name.length > 0){
          console.log("responseeeee");
          this.carsService.insertImage(this.selectedImage, carResp.id).subscribe(
            {next :response => {
              console.log(response);
              this.router.navigate([`/cars/${carResp.id}/${carResp.plate}`]);
             }}
           )

          }


          //alert(`Your order has been sent . \nOrder tracking number : ${response.orderTrackingNumber}`);
        },
        error: err => {
          isError = true;
        }
      }
    )

    //TODO


    //call REST API via the checoutService
    let isError = false;

    console.log(isError);

  }
}


