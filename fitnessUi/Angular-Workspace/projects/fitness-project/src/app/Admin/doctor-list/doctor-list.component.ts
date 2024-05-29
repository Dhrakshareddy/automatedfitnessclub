// src/app/doctor-list/doctor-list.component.ts
import { Component, OnDestroy, OnInit } from '@angular/core';
import { DoctorService } from '../service/doctor.service';
import { Doctor } from '../model/doctor.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit,OnDestroy {
  doctors: Doctor[] = [];
  private subscription!: Subscription;

  constructor(private doctorService: DoctorService) { }

  ngOnInit(): void {
    this.getDoctors()
  }
  getAll() {
    this.doctorService.getDoctors().subscribe(doctor => {
      this.doctors = doctor
    })
  }

  getDoctors() {
    this.doctorService.getDoctors().subscribe((doctors: Doctor[]) => {
     
      this.doctors = doctors;
    });
  }
  deleteDoctor(id: number): void {
    this.doctorService.deleteDoctor(id).subscribe((res) => {

      this.getDoctors();
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();

    }
  }

}