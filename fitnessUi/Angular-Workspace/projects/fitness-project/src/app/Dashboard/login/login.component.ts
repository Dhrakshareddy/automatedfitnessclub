import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { Login } from '../../../Models/login.model';
import { UserService } from '../../../Services/user.service';
import { AuthService } from '../dash-board/auth.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { FitnesstypeDetailsService } from '../../Auth/Auth/fitness-details.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit,OnDestroy {

  public login = new Login('', '');
  public loginForm: any;
  public values: any[] = [];
  public details: any;
  public isLoggedIn: boolean = false;
  private subscription!:Subscription

  constructor(
    private formBuilder: FormBuilder,
    private service: UserService,
    private router: Router,
    private auth: AuthService,
    private snackBar: MatSnackBar,
    private  fitnessService :FitnesstypeDetailsService
  ) { }
 
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(2)]],
    });
  }
  private showSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.panelClass = ['custom-snackbar'];
    config.duration = 2000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, 'close', config);
  }
  onSubmit(): void {
    // this.auth.loginedIn(this.login);

    const username = this.loginForm.get('name').value;
    const password = this.loginForm.get('password').value;

    console.log('Username:', username);
    console.log('Password:', password);

    if (username === 'admin' && password === '0000') {
      console.log(username, password)
      localStorage.setItem("role", 'ADMIN')
      this.auth.isLogged = true;
      this.showSnackBar("give correct login credentials");
      this.router.navigate(['/login']);
   
    }
    else if (this.loginForm.valid) {
      console.log("else");
      this.auth.loginedIn(this.login);
      this.login.name = this.loginForm.value.name;
      this.login.password = this.loginForm.value.password;
      console.log(this.login.name);
      console.log(this.login.password);
      this.values = this.loginForm.value;
      this.details = JSON.stringify(this.values);

      console.log(this.details);



      this.service.adminLoginDetails(this.login).subscribe(
        (response: any) => {

          const json = response.JSON;
          console.log('Response user:', response)
          if (response != null) {
              if(response.role!=='admin'){
                console.log(response.role)
                localStorage.setItem("user", JSON.stringify(response));
                localStorage.setItem("role", response.role)
                this.auth.isLogged = true;
                this.router.navigate(['/admin']);
                console.log('Login successful');     
            }
          } else {

          }
        },
        (error: any) => {
          console.error('Error:', error);

          // Handle specific error cases if needed
          if (error.status === 401) {
            console.log('Unauthorized - Incorrect credentials');
          } else {
            console.log('Login failed - Unexpected error');
          }
        }
      );
      this.fitnessService.loginDetails(this.login).subscribe(
        (response: any) => {

          const json = response.JSON;
          console.log('Response user:', response)
          if (response != null) {
              if(response.role!=='admin'){
                console.log(response.role)
                localStorage.setItem("user", JSON.stringify(response));
                localStorage.setItem("role", response.role)
                this.auth.isLogged = true;
                this.router.navigate(['/verticalnav']);
                console.log('Login successful');     
            }
          } else {

          }
        },
        (error: any) => {
          console.error('Error:', error);

          // Handle specific error cases if needed
          if (error.status === 401) {
            console.log('Unauthorized - Incorrect credentials');
          } else {
            console.log('Login failed - Unexpected error');
          }
        }
      );

      this.service.doctorLoginDetails(this.login).subscribe(
        (response: any) => {

          const json = response.JSON;
          console.log('Response:', response);


          if (response != null) {

            if (response.role === 'DOCTOR') {
              localStorage.setItem("doctor", JSON.stringify(response));
              this.auth.isLogged = true;
              this.router.navigate(['/doctorDashboard']);
            }
            else {
              this.router.navigate(['/doctorDashboard']);
            }

            console.log('Login successful');
          } else {
            this.router.navigate(['/doctorDashboard']);
          }
        },
        (error: any) => {
          console.error('Error:', error);


          if (error.status === 401) {
            console.log('Unauthorized - Incorrect credentials');
          } else {
            console.log('Login failed - Unexpected error');
          }
        }
      );

      this.service.trainerLoginDetails(this.login).subscribe(
        (response: any) => {

          const json = response.JSON;
          console.log('Response 000000000000:', response);

          if (response != null) {

            if (response.role === 'TRAINER') {
              localStorage.setItem("trainer", JSON.stringify(response));
              this.auth.isLogged = true;
              this.router.navigate(['/trainer']);
            }


            else {
              this.router.navigate(['/about']);
            }

            console.log('Login successful');
          }

          else {
            // Login failed logic
            this.router.navigate(['/home']);
          }
        },
        (error: any) => {
          console.error('Error:', error);

          if (error.status === 401) {
            console.log('Unauthorized - Incorrect credentials');
          } else {
            this.showSnackBar("The system is temporarily unavailable.Please try again.");
            console.log('Login failed - Unexpected error');
          }
        }
      );
    }

  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  onUserTypeChange(event: Event) {
    // Cast the event target to HTMLSelectElement
    const selectedValue = (event.target as HTMLSelectElement).value;
    
    if (selectedValue === 'doctor') {
      this.router.navigateByUrl('/doctorRegister');
    } else if (selectedValue === 'trainer') {
      this.router.navigateByUrl('/trainerRegister');
    } else if (selectedValue === 'user') {
      this.router.navigateByUrl('/userlogin');
    }
  }
}