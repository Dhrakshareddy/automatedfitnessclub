import { Component, OnDestroy, OnInit } from '@angular/core';
import { Equipment } from '../model/equipment.model';
import { EquipmentService } from '../service/eqipment.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrl: './equipment-list.component.css'
})
export class EquipmentListComponent implements OnInit,OnDestroy {
  equipment: Equipment[] = [];
  private subscription!:Subscription

  constructor(private equipmentService: EquipmentService) { }

  ngOnInit(): void {

    this.getEquipment();

  }
  getEquipment() {
    this.equipmentService.getEquipment().subscribe((equipment: Equipment[]) => {
      this.equipment = equipment;
    });
  }
  
  deleteEquipment(id: number): void {
    (id);

    this.equipmentService.deleteEquipment(id).subscribe((res) => {

      this.getEquipment();
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();

    }
  }
}



