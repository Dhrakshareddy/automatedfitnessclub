// src/app/user/user-list/user-list.component.ts
import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../../Models/user.model';
import { UserService } from '../service/user.service';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit,OnDestroy {
  users: User[] = [];
  private subscription!:Subscription



  constructor(private userService: UserService) { }

  ngOnInit(): void {

    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe((users: User[]) => {


      this.users = users

    });
  }
  getAll() {
    this.userService.getUsers().subscribe(users => {
      this.users = users
    })
  }
  deleteUser(id: number): void {
    (id);

    this.userService.deleteUser(id).subscribe((res) => {

      this.getUsers();
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
