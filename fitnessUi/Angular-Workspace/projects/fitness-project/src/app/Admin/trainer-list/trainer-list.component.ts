
import { Component, OnDestroy, OnInit } from '@angular/core';

import { TrainerService } from '../service/trainer.service';
import { Trainer } from '../model/trainer.model';
import { Subscription } from 'rxjs';





@Component({
  selector: 'app-trainer-list',

  templateUrl: './trainer-list.component.html',
  styleUrl: './trainer-list.component.css'
})
export class TrainerListComponent implements OnInit,OnDestroy {
  trainers: Trainer[] = [];
  private subscription!:Subscription



  constructor(private trainerService: TrainerService) { }

  ngOnInit(): void {
    this.getTrainers();


  }
  
  getTrainers() {
    this.trainerService.getTrainers().subscribe((trainers: Trainer[]) => {

      
      this.trainers = trainers

    });
  }
  getAll() {
    this.trainerService.getTrainers().subscribe(trainer => {
      this.trainers = this.trainers
    })
  }

  deleteTrainer(id: number): void {


    this.trainerService.deleteTrainer(id).subscribe((res) => {

      this.getTrainers();
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }


}

