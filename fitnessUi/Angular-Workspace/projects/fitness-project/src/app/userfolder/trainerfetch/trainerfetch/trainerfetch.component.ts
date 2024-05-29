import { Component, OnDestroy, OnInit } from '@angular/core';
import { fetchTrainer } from '../../../../Models/fetchTrainer.model';
import { TrainerFetchService } from '../../../../Services/TrainerFetch.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-trainerfetch',

  templateUrl: './trainerfetch.component.html',
  styleUrl: './trainerfetch.component.css'
})
export class TrainerfetchComponent implements OnInit,OnDestroy{

  private subscription!:Subscription
  trainerName: string = ''; 
  trainers: fetchTrainer[] = [];

  constructor(private trainerFetchService: TrainerFetchService) { }

  ngOnInit(): void {
    this.fetchTrainer();
  }

  fetchTrainer() {
    this.trainerFetchService.getTrainerByName(this.trainerName)
      .subscribe((data: fetchTrainer[]) => {
        (data); 
        this.trainers=data;
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
