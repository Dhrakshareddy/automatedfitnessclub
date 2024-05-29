import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from './auth.service';

import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from '../../Admin/service/user.service';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrl: './forgotpassword.component.css'
})
export class ForgotpasswordComponent {
  forgotPasswordForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private service: UserService, private snackBar: MatSnackBar, private router: Router) {
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]

    });
  }

  submitResetRequest(): void {
    if (this.forgotPasswordForm.valid) {
      const email = this.forgotPasswordForm.value.email;

      const password = this.forgotPasswordForm.value.password;
      this.router.navigate(["/login"])




      this.service.updatePassword(email, password).subscribe(
        (response: any) => {
          this.showSnackBar("password updated successfuly");

          

        },
        (error: any) => {
          this.showSnackBar("password updated failure")

          console.error('Error requesting password reset:', error);
        }
      );
    }
  }
  private showSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.panelClass = ['custom-snackbar'];
    config.duration = 2000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, 'close', config);
  }
}