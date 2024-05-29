import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { RandomCodeService } from '../../../Services/RandomCodeService';
import { UserService } from '../../Admin/service/user.service';




@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrl: './forgot.component.css'
})
export class ForgotComponent {
  forgotForm: FormGroup;


  // public passwordUpdate:PasswordUpdateRequest=new PasswordUpdateRequest("","","","");
  // public email:PasswordUpdateRequest=new PasswordUpdateRequest("","","","");

  constructor(private fb: FormBuilder,private router:Router,private service:UserService,
    private snackBar:MatSnackBar,private random:RandomCodeService){

      this.forgotForm = this.fb.group({
        email: ['', [Validators.required]],
        password: ['', [Validators.required]],
        
        verificationCode: ['', [Validators.required]]
    
      });
    }submitResetRequest(): void {
    if (this.forgotForm.valid) {
      const email = this.forgotForm.value.email;

      const password = this.forgotForm.value.password;
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
