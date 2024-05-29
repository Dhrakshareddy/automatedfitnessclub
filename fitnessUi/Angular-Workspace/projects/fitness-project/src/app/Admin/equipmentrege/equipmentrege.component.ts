import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EquipmentService } from '../service/eqipment.service';
import { Equipment } from '../model/equipment.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-equipmentrege',
  templateUrl: './equipmentrege.component.html',
  styleUrl: './equipmentrege.component.css',
})
export class EquipmentregeComponent implements OnInit, OnDestroy {
  private subscription!: Subscription;
  equipmentForm!: FormGroup;
  isEditMode = false;
  equipmentDetails: any;
  equipmentId: any;


  constructor(
    private formBuilder: FormBuilder,
    private equipmentService: EquipmentService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar:MatSnackBar
  ) { }

  ngOnInit(): void {
    this.equipmentId = this.route.snapshot.paramMap.get("id");

    this.equipmentForm = this.formBuilder.group({
      equipmentName: ['', Validators.required],
      description: ['', Validators.required],
      brand: ['', Validators.required],
      quantity: [0, Validators.required],
      purchaseDate: [new Date(), Validators.required],


    });



    if (this.equipmentId != 0 && this.equipmentId != null) {
      this.getDetails(this.equipmentId);
    }


    this.subscription = this.route.paramMap.subscribe(params => {
      const id = +params.get('id')!;
      if (!isNaN(id)) {
        this.isEditMode = true;
        this.equipmentId = id;
        this.equipmentService.getEquipmentById(id).subscribe((equipment: Equipment) => {
          this.equipmentForm.patchValue(equipment);
        });

      }
    });

  }


  update() {
    const equipment: Equipment = this.equipmentForm.value;
    equipment.equipmentId = this.equipmentDetails.equipmentId

    this.equipmentService.updateequipment(equipment).subscribe(() => {
      this.showSnackBar('Equipment updated successfully!');
      this.router.navigate(['/admin/equipment']);
    });
  }

  getDetails(doctorId: any): void {
    if (doctorId != null) {
      this.equipmentService.getEquipmentById(doctorId).subscribe(
        (data: any) => {
          this.equipmentDetails = data;

          this.equipmentForm.patchValue(this.equipmentDetails);
        }, (error: any) => {
          (error)
        }

      )
    }
  }


  onSubmit(): void {
    const equipment: Equipment = this.equipmentForm.value;


    if (this.isEditMode) {
      equipment.equipmentId = this.equipmentId;
    }

    this.equipmentService.saveEquipment(equipment).subscribe(() => {
      this.showSnackBar('Equipment added successfully!');
      this.router.navigate(['/admin/equipment']);
    });
  }
showSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, 
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();

    }
  }
}