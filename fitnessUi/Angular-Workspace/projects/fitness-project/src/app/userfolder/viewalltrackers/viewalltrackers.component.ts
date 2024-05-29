import { Component } from '@angular/core';

@Component({
  selector: 'app-viewalltrackers',
 
  templateUrl: './viewalltrackers.component.html',
  styleUrl: './viewalltrackers.component.css'
})
export class ViewalltrackersComponent {
  days: number[] = Array.from({ length: 7 }, (_, i) => i + 1); // Assuming 31 days in a month

  // Sample data (you can replace this with your actual data)
  sleepTracker: { [key: number]: string } = {
    1: '8 hours',
    2: '7.5 hours',
    3: '8 hours',
    4: '7.5 hours',
    5: '8 hours',
    6: '7.5 hours', 
    7: '8 hours',
    
   
  };
  medicineTracker: { [key: number]: string } = {
    1: 'Aspirin',
    2: 'Antibiotic',
    3: 'Aspirin',
    4: 'Antibiotic',
    5: 'Aspirin',
    6: 'Antibiotic',
    7: 'Aspirin',
    
   
  };
  handwashTracker: { [key: number]: string } = {
    1: 'Yes',
    2: 'No',
    3: 'Yes',
    4: 'No',
    5: 'Yes',
    6: 'No',
    7: 'Yes',
   
    
  };
  heartRateTracker: { [key: number]: string } = {
    1: '80 bpm',
    2: '75 bpm',
    3: '80 bpm',
    4: '75 bpm',
    5: '80 bpm',
    6: '75 bpm',
    7: '80 bpm',
   
    
  };
  respiratoryRateTracker: { [key: number]: string } = {
    1: '18 bpm',
    2: '20 bpm',
    3: '18 bpm',
    4: '20 bpm',
    5: '18 bpm',
    6: '20 bpm',
    7: '18 bpm',
   
   
  };
}
