import { UserService } from '../../services/user.service';
import { User } from './../../common/user';
import { Component } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  users: User[]= [];
  constructor(private userService: UserService){}
  ngOnInit(): void {
    this.listUsers();
   }
   listUsers() {
    this.userService.getUsers()
      .subscribe(response => this.users = response);

  }
}
