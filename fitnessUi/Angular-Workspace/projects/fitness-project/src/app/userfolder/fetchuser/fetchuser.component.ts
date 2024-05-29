import { Component, OnDestroy, OnInit } from '@angular/core';
import { fetchUser } from '../../../Models/fetchUser.model';
import { UserFetchService } from '../../../Services/UserFetch.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-fetchuser',
  templateUrl: './fetchuser.component.html',
  styleUrl: './fetchuser.component.css'
})
export class FetchuserComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  userName: string = ''; 
  userList: fetchUser[] = [];

  constructor(private userFetchService: UserFetchService) { }

  ngOnInit(): void {
    this.fetchUser();
  }

  fetchUser() {
    this.userFetchService.getUserByName(this.userName)
      .subscribe((data: fetchUser[]) => {
        (data); 
        this.userList=data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
