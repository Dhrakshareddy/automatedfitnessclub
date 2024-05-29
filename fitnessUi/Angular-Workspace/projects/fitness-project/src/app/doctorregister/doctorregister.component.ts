import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DoctorService } from '../Admin/service/doctor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Doctor } from '../Admin/model/doctor.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-doctorregister',
  templateUrl: './doctorregister.component.html',
  styleUrl: './doctorregister.component.css'
})
export class DoctorregisterComponent  implements OnInit, OnDestroy{
  private subscription!: Subscription;
  doctorForm!: FormGroup;
  isEditMode = false;
  doctorId: any = 0;
  doctorDetails: any;


  constructor(
    private formBuilder: FormBuilder,
    private doctorService: DoctorService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }
  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

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
  getDetails(doctorId: any) {
    throw new Error('Method not implemented.');
  }
  onSubmit(): void {
    const doctor: Doctor = this.doctorForm.value;
    (doctor);

    if (this.isEditMode) {
      doctor.doctorId = this.doctorId; // Use the correct property name
    }

    this.doctorService.saveDoctor(doctor).subscribe(() => {
      this.showSnackBar('doctor saved successfully!');
      
      this.router.navigate(['/admin/doctors']);
    });
  }

  update() {
    const doctor: Doctor = this.doctorForm.value;
    doctor.doctorId = this.doctorDetails.doctorId

    this.doctorService.updatedoctor(doctor).subscribe(() => {
      this.router.navigate(['/admin/doctors']);
    });
  }
  
  showSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, // Duration in milliseconds
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }
}
