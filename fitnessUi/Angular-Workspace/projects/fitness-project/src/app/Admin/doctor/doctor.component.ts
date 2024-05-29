import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Subscription } from 'rxjs';

import { DoctorService } from '../service/doctor.service';
import { Doctor } from '../model/doctor.model';


@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit, OnDestroy {
  private subscription!: Subscription;
  doctorForm!: FormGroup;
  isEditMode = false;
  doctorId: any = 0;
  doctorDetails: any;


  constructor(
    private formBuilder: FormBuilder,
    private doctorService: DoctorService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.doctorId = this.route.snapshot.paramMap.get("id");

    this.doctorForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
      age: ['', Validators.required],
      yearOfExperience: [0, Validators.required],
      contactNumber: [0, Validators.required],
      shiftTimings: ['', Validators.required],
      specialization: ['', Validators.required],
      role: ['', Validators.required],
      doctorCode: ['', Validators.required],




    });



    if (this.doctorId != 0 && this.doctorId != null) {
      this.getDetails(this.doctorId);
    }


    this.subscription = this.route.paramMap.subscribe(params => {
      const id = +params.get('id')!;
      if (!isNaN(id)) {
        this.isEditMode = true;
        this.doctorId = id;
        this.doctorService.getDoctorById(id).subscribe((doctor: Doctor) => {
          this.doctorForm.patchValue(doctor);
        });

      }
    });

  }


  update() {
    const doctor: Doctor = this.doctorForm.value;
    doctor.doctorId = this.doctorDetails.doctorId

    this.doctorService.updatedoctor(doctor).subscribe(() => {
      this.router.navigate(['/admin/doctors']);
    });
  }


  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();

    }
  }

  getDetails(doctorId: any): void {
    if (doctorId != null) {
      this.doctorService.getDoctorById(doctorId).subscribe(
        (data: any) => {
          this.doctorDetails = data;
          (JSON.stringify(this.doctorDetails))
          this.doctorForm.patchValue(this.doctorDetails);
        }, (error: any) => {
          (error)
        }

      )
    }
  }

  // src/app/doctor/doctor.component.ts
  // src/app/doctor/doctor.component.ts
  onSubmit(): void {
    const doctor: Doctor = this.doctorForm.value;
    (doctor);

    if (this.isEditMode) {
      doctor.doctorId = this.doctorId; // Use the correct property name
    }

    this.doctorService.saveDoctor(doctor).subscribe(() => {
      this.router.navigate(['/admin/doctors']);
    });
  }


}