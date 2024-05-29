import { Component, OnDestroy, OnInit } from '@angular/core';
import { fetchDoctor } from '../../../../Models/fetchDoctor.model';
import { DoctorFetchService } from '../../../../Services/DoctorFetch.service';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-doctorfetch',

  templateUrl: './doctorfetch.component.html',
  styleUrl: './doctorfetch.component.css'
})
export class DoctorfetchComponent implements OnInit,OnDestroy{

  private subscription!:Subscription
  doctorName: string = ''; 
  doctorList: fetchDoctor[] = [];

  constructor(private doctorFetchService: DoctorFetchService) { }

  ngOnInit(): void {
    this.fetchDoctor();
  }

  fetchDoctor() {
    this.doctorFetchService.getDoctorByName(this.doctorName)
      .subscribe((data: fetchDoctor[]) => {
        (data); 
        this.doctorList=data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
