import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrl: './dash-board.component.css'
})
export class DashBoardComponent {

  constructor(public auth: AuthService, private snackBar: MatSnackBar) {

  }

  public isLoggedIn: boolean = false;


  logout() {
    this.isLoggedIn = false;
    localStorage.clear();
    this.auth.logout();

    this.showSnackBar("Logout successfull");
  }
  
  private showSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.panelClass = ['custom-snackbar'];
    config.duration = 2000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, 'close', config);
  }

}