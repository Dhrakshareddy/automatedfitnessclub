import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { plansservice } from './payment.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Payment } from './payment.model';
import { ActivatedRoute, Router } from '@angular/router';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { Subscription } from 'rxjs';





@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit,OnDestroy{

  private subscription!:Subscription
  @ViewChild('pdfContent') pdfContent!: ElementRef<any>;
  plansForm: FormGroup;
  loading = false;
  showAdditionalBenefits: boolean = false;
  spinnerVisible = false;
  getUserName: any;
  payment: Payment = {
    paymentId: 0,
    paymentAmount: 0,
    paymentMode: '',
    paymentDate: new Date(),
    status: false,
    username: '',
    membershipType: ''
  };


  constructor(private fb: FormBuilder,private route: ActivatedRoute,private plansService: plansservice,private router:Router, private snackBar: MatSnackBar) {

    this.plansForm = this.fb.group({
    
    });

  }
    ngOnInit(): void {
      
      this.getUserName=localStorage.getItem("user");
      if(this.getUserName)
      {
      this.getUserName=JSON.parse(this.getUserName);
      this.getUserName.name;
      this.payment.username = this.getUserName.name;
      }
      else{
        ("trainer not found");
      }
    }
    
   
  toggleAdditionalBenefits() {
    this.showAdditionalBenefits = !this.showAdditionalBenefits;
  }

  
  generatePdf(): void {
    const element = this.pdfContent.nativeElement;

    if (element) {
      html2canvas(element).then((canvas) => {
        const contentDataURL = canvas.toDataURL('image/png');
        const pdf = new jsPDF();
        pdf.addImage(contentDataURL, 'PNG', 0, 0, 210, 297);
        pdf.save('payment_receipt.pdf');
      });
    } else {
      console.error('Element with ID "pdfContent" not found.');
    }
  }
  
  makePayment():void {
   
    if (this.plansForm.valid) {
      this.loading = true;
      this.spinnerVisible = true;
     
      setTimeout(() => {
        this.plansService.makePayment(this.payment).subscribe( (data) => {
          this.payment = data;
         
          this.loading = false;
          this.spinnerVisible = false; 
           this.showSnackBar('Payment done successfully!');
          this.generatePdf();
           this.router.navigate(['/userlogin/historymedical'])
        },
         
          (error: any) => {
           
            this.loading = false;
            this.spinnerVisible = false; 
                        console.error('Error making payment:', error);
            this.showSnackBar('An error occurred while processing your payment. Please try again later.');
          }
        );
      }, 3000); 
    } else {
      alert('Please fill in all required fields.');
    }
  }
  
  showSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}





