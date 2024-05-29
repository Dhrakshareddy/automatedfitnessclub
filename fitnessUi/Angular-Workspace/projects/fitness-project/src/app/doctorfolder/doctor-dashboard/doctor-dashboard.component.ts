import { Component } from '@angular/core';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent {
  public isNavbarCollapsed = true;

  public toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }
}