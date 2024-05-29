import { NgModule } from '@angular/core';
import * as emailjs from 'emailjs-com';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DashBoardComponent } from './Dashboard/dash-board/dash-board.component';
import { AdminnavComponent } from './Admin/adminnav/adminnav.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { UserFormComponent } from './Admin/user-form/user-form.component';
import { UserListComponent } from './Admin/user-list/user-list.component';
import { DoctorComponent } from './Admin/doctor/doctor.component';
import { DoctorListComponent } from './Admin/doctor-list/doctor-list.component';
import { TrainerListComponent } from './Admin/trainer-list/trainer-list.component';
import { TrainerregComponent } from './Admin/trainerreg/trainerreg.component';
import { TrainerService } from '../Services/trainer.service';
import { UserService } from '../Services/user.service';
import { DoctorService } from '../Services/doctor.service';
import { RouterModule } from '@angular/router';
import { ExerciseComponent } from './trainerfolder/exercise/exercise.component';
import { TrainerUsersComponent } from './trainerfolder/trainer-users/trainer-users.component';
import { TrainerComponent } from './trainerfolder/trainer/trainer.component';

import { WorkoutComponent } from './trainerfolder/workout/workout.component';
import { DoctorDashboardComponent } from './doctorfolder/doctor-dashboard/doctor-dashboard.component';
import { MedicalHistoryComponent } from './doctorfolder/medical-history/medical-history.component';
import { MyPatientComponent } from './doctorfolder/my-patient/my-patient.component';
import { NutritionTableComponent } from './doctorfolder/nutrition-table/nutrition-table.component';
import { PaymentComponent } from './doctorfolder/payment/payment.component';
import { AttendanceComponent } from './trainerfolder/attendance/attendance.component';
import { ClassschedulingComponent } from './trainerfolder/classscheduling/classscheduling.component';
import { UserclassComponent } from './trainerfolder/userclass/userclass.component';

import { HomenavComponent } from './userfolder/homenav/homenav.component';
import { InviteComponent } from './userfolder/invite/invite.component';



import { StoreComponent } from './userfolder/store/store.component';

import { UserProfileComponent } from './userfolder/user-profile/user-profile.component';
import { VerticalnavComponent } from './userfolder/verticalnav/verticalnav.component';
import { ViewalltrackersComponent } from './userfolder/viewalltrackers/viewalltrackers.component';
import { DrinkwaterComponent } from './userfolder/drinkwater/drinkwater.component';
import { PlansComponent } from './userfolder/plans/plans.component';
import { EquipmentListComponent } from './Admin/equipment-list/equipment-list.component';
import { EquipmentregeComponent } from './Admin/equipmentrege/equipmentrege.component';
import { MembershipComponent } from './Admin/membership/membership.component';

import { MedicalhistoryFetchComponent } from './doctorfolder/medicalhistory-fetch/medicalhistory-fetch.component';
import { MorehomeComponent } from './userfolder/morehome/morehome.component';
import { UserClassNameComponent } from './userfolder/user-class-name/user-class-name/user-class-name.component';
import { AttendanceUserComponent } from './userfolder/attendance-user/attendance-user/attendance-user.component';

import { DoctorfetchComponent } from './userfolder/doctorfetch/doctorfetch/doctorfetch.component';
import { TrainerfetchComponent } from './userfolder/trainerfetch/trainerfetch/trainerfetch.component';

import { FetchuserComponent } from './userfolder/fetchuser/fetchuser.component';


import { FetchnutritionComponent } from './userfolder/fetchnutrition/fetchnutrition.component';
import { LoadingSpinnerComponent } from './doctorfolder/loading-spinner/loading-spinner.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MembershipregComponent } from './Admin/membershipreg/membershipreg.component';

import { ForgotpasswordComponent } from './Dashboard/forgotpassword/forgotpassword.component';
import { ServicesComponent } from './Dashboard/services/services.component';

import { DoctorprofileComponent } from './doctorfolder/doctorprofile/doctorprofile.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BurnatleastComponent } from './userfolder/burnatleast/burnatleast.component';
import { WorkoutListComponent } from './trainerfolder/workoutlist/workout-list.component';

import { ProgressComponent } from './trainerfolder/progress/progress.component';

import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { RegisterComponent } from './Dashboard/register/register.component';
import { CommonModule } from '@angular/common';
import { UserfetchComponent } from './doctorfolder/userfetch/userfetch.component';
import { UserExerciseComponent } from './userfolder/user-exercise/user-exercise/user-exercise.component';
import { FetchpaymentComponent } from './userfolder/fetchpayment/fetchpayment.component';
import { FetchworkoutsComponent } from './trainerfolder/fetchworkouts/fetchworkouts.component';
import { ProgressReportsComponent } from './userfolder/progress-reports/progress-reports/progress-reports.component';
import jsPDF from 'jspdf';
import { ReactiveFormsModule } from '@angular/forms';
import { ForgotComponent } from './Dashboard/forgot/forgot.component';
import { VerificationCodeComponent } from './Dashboard/verification-code/verification-code.component';
import { DoctorregisterComponent } from './doctorregister/doctorregister.component';
import { TrainerselfregComponent } from './trainerselfreg/trainerselfreg.component';
@NgModule({
  declarations: [
    AppComponent,
    DashBoardComponent,
    LoginComponent,
    AdminnavComponent,
    UserFormComponent,
    UserListComponent,
    DoctorComponent,
    DoctorListComponent,
    TrainerListComponent,
    TrainerregComponent,
    WorkoutListComponent,
  RegisterComponent,
    WorkoutComponent,
    TrainerComponent,
    ExerciseComponent,
    TrainerUsersComponent,
    

    MedicalHistoryComponent,
    NutritionTableComponent,
    PaymentComponent,
    DoctorDashboardComponent,
    MyPatientComponent,


    AttendanceComponent,
     ClassschedulingComponent,
    
     UserclassComponent,
     TrainerComponent,
   
    WorkoutComponent,
    ProgressComponent,

    AppComponent,
   HomenavComponent,
   StoreComponent,
   MorehomeComponent,
   PlansComponent,

   DrinkwaterComponent,
   
   InviteComponent,
  
   
   UserProfileComponent,
   ViewalltrackersComponent,
   VerticalnavComponent,
   DoctorregisterComponent,
   TrainerComponent,
   TrainerselfregComponent,

AttendanceComponent,
ClassschedulingComponent,

UserclassComponent,

   UserFormComponent,
    AdminnavComponent,
   MembershipComponent,
   TrainerregComponent,
   DoctorListComponent,
   DoctorComponent,
   UserFormComponent,
   UserListComponent,
   TrainerListComponent,
   EquipmentListComponent,
   EquipmentregeComponent,
   MedicalhistoryFetchComponent,
   
   MorehomeComponent,

   UserClassNameComponent,
   AttendanceUserComponent,
   FetchnutritionComponent,
   DoctorfetchComponent,
   TrainerfetchComponent,

   FetchuserComponent,

  
   ViewalltrackersComponent,
   LoadingSpinnerComponent,
   MembershipregComponent,
   ServicesComponent,
   
   ForgotpasswordComponent,
   EquipmentregeComponent,
   DoctorprofileComponent,
   BurnatleastComponent,
   WorkoutComponent,
   UserfetchComponent,
   UserExerciseComponent,
   FetchpaymentComponent,
   FetchworkoutsComponent,
   ProgressReportsComponent,
   ForgotComponent,
   VerificationCodeComponent,
   DoctorregisterComponent,
   TrainerselfregComponent
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
   ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    BrowserAnimationsModule,   
    MatMenuModule,
    MatIconModule,
    FormsModule 
  ],
  providers: [TrainerService,UserService,DoctorService, provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    // Initialize EmailJS with your API key
    emailjs.init('btQKPkJ6-izF7l6ef');
  }
 }
