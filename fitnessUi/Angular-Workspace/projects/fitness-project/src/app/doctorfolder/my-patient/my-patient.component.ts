import { Component } from '@angular/core';

@Component({
  selector: 'app-my-patient',
  templateUrl: './my-patient.component.html',
  styleUrl: './my-patient.component.css'
})
export class MyPatientComponent {
  public patients = [
    { id: 1, name: 'John Doe', prescription: 'Prescription for John Doe' },
    { id: 2, name: 'Jane Smith', prescription: 'Prescription for Jane Smith' },
   
  ];



  public newPatient: any = {
    id: null,
    name: '',
    prescription: ''
  };

  public addPatient() {
    this.newPatient.id = this.generatePatientId();
    this.patients.push({ ...this.newPatient });
    this.resetNewPatient();
  }

  private generatePatientId(): number {
   
    const lastPatient = this.patients[this.patients.length - 1];
    return lastPatient ? lastPatient.id + 1 : 1;
  }

  private resetNewPatient() {
    this.newPatient = { id: null, name: '', prescription: '' };
  }
  public selectedPatient: any = null;

  public editPatient(patient: any) {
    this.selectedPatient = { ...patient }; 
  }

  public saveChanges() {
   
    const index = this.patients.findIndex(p => p.id === this.selectedPatient.id);
    if (index !== -1) {
      this.patients[index] = this.selectedPatient;
    }

    this.selectedPatient = null;
  }

  public deletePatient(patientId: number) {
    const index = this.patients.findIndex(patient => patient.id === patientId);
    if (index !== -1) {
      this.patients.splice(index, 1); 
    }
  }
}