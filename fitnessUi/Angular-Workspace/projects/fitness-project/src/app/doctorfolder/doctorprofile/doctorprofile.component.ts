import { Component } from '@angular/core';
import { Doctor } from '../../Admin/model/doctor.model';
import { DoctorService } from '../../Admin/service/doctor.service';

@Component({
  selector: 'app-doctorprofile',
  templateUrl: './doctorprofile.component.html',
  styleUrl: './doctorprofile.component.css'
})
export class DoctorprofileComponent {
  public doctor :Doctor=new Doctor(0,'','','',0,0,'','');
  constructor(private doctorService: DoctorService) {

    var getDoctor=localStorage.getItem("doctor");
    if(getDoctor)
    {
    this.doctor=JSON.parse(getDoctor);
    (this.doctor);
    }
    else{
      console.error("doctor not found");
    }

  }
 

}
