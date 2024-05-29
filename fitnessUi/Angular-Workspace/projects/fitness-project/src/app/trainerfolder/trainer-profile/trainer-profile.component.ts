import { Component, OnInit } from '@angular/core';
import { Trainer } from '../../Admin/model/trainer.model';
import { TrainerService } from '../../Admin/service/trainer.service';

@Component({
  selector: 'app-trainer-profile',
  templateUrl: './trainer-profile.component.html',
  styleUrl: './trainer-profile.component.css'
})
export class TrainerProfileComponent {
  public trainer :Trainer=new Trainer(0,'','','',0,'','','');
  constructor(private trainerService: TrainerService) {

    var getTrainer=localStorage.getItem("trainer");
    if(getTrainer)
    {
    this.trainer=JSON.parse(getTrainer);
  
    }
    else{
      ("trainer not found");
    }

  }
 
}



