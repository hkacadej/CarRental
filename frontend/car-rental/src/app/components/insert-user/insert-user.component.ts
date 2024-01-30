import { User } from './../../authentication/common/user';
import { Component } from '@angular/core';
import { CarsService } from '../../service/cars.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/authentication/services/user.service';
import { UserDto } from 'src/app/authentication/common/user-dto';

@Component({
  selector: 'app-insert-user',
  templateUrl: './insert-user.component.html',
  styleUrls: ['./insert-user.component.css']
})

export class InsertUserComponent {
  userFormGoup!: FormGroup;
  userRoles!: String[];
  selectedImage: File | null = null;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private carsService: CarsService) { }

  ngOnInit(): void {
    this.userFormGoup = this.formBuilder.group({
      user: this.formBuilder.group({
        username: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        password: new FormControl('', [Validators.required,
        Validators.minLength(2)]),

        roles: new FormControl('', Validators.required)
      })
    });

    this.carsService.getRoles().subscribe(
      data => {
        console.log(data)
        this.userRoles = data;
      });

  }

  onImageSelected(event: any): void {
    this.selectedImage = event.target.files[0];
  }

  get username() { return this.userFormGoup.get('user.username'); }
  get password() { return this.userFormGoup.get('user.password'); }

  get roles() { return this.userFormGoup.get('user.roles'); }


  onSubmit() {

    if (this.userFormGoup.invalid) {
      console.log(true);
      this.userFormGoup.markAllAsTouched();
      return;
    }

    //set up user
    let user = new UserDto();
    user = this.userFormGoup.controls['user'].value;
    //user.roles = user.roles.toString().split(",");
    console.log(user);
    this.userService.addUser(user).subscribe(
      data => {
        console.log(data);
      }
    )

  }
}


