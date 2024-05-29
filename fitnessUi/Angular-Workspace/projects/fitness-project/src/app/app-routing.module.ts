
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashBoardComponent } from './Dashboard/dash-board/dash-board.component';
import { AdminnavComponent } from './Admin/adminnav/adminnav.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { UserListComponent } from './Admin/user-list/user-list.component';
import { DoctorListComponent } from './Admin/doctor-list/doctor-list.component';
import { DoctorComponent } from './Admin/doctor/doctor.component';
import { TrainerListComponent } from './Admin/trainer-list/trainer-list.component';
import { TrainerregComponent } from './Admin/trainerreg/trainerreg.component';
import { UserFormComponent } from './Admin/user-form/user-form.component';
import { AboutComponent } from './Dashboard/about/about.component';

import { HomeComponent } from './Dashboard/home/home.component';
import { ServicesComponent } from './Dashboard/services/services.component';
import { TrainerComponent } from './trainerfolder/trainer/trainer.component';
import { ExerciseComponent } from './trainerfolder/exercise/exercise.component';
import { TrainerUsersComponent } from './trainerfolder/trainer-users/trainer-users.component';
import { WorkoutComponent } from './trainerfolder/workout/workout.component';
import { MedicalHistoryComponent } from './doctorfolder/medical-history/medical-history.component';

import { NutritionTableComponent } from './doctorfolder/nutrition-table/nutrition-table.component';
import { DoctorDashboardComponent } from './doctorfolder/doctor-dashboard/doctor-dashboard.component';

import { UserclassComponent } from './trainerfolder/userclass/userclass.component';
import { AttendanceComponent } from './trainerfolder/attendance/attendance.component';
import { ClassschedulingComponent } from './trainerfolder/classscheduling/classscheduling.component';

import { TrainerProfileComponent } from './trainerfolder/trainer-profile/trainer-profile.component';
import { VerticalnavComponent } from './userfolder/verticalnav/verticalnav.component';
import { PlansComponent } from './userfolder/plans/plans.component';


import { HomenavComponent } from './userfolder/homenav/homenav.component';


import { DrinkwaterComponent } from './userfolder/drinkwater/drinkwater.component';
import { ViewalltrackersComponent } from './userfolder/viewalltrackers/viewalltrackers.component';
import { PaymentComponent } from './doctorfolder/payment/payment.component';
import { EquipmentListComponent } from './Admin/equipment-list/equipment-list.component';
import { MembershipComponent } from './Admin/membership/membership.component';
import { MembershipregComponent } from './Admin/membershipreg/membershipreg.component';
import { EquipmentregeComponent } from './Admin/equipmentrege/equipmentrege.component';
import { MyPatientComponent } from './doctorfolder/my-patient/my-patient.component';
import { MedicalhistoryFetchComponent } from './doctorfolder/medicalhistory-fetch/medicalhistory-fetch.component';
import { MorehomeComponent } from './userfolder/morehome/morehome.component';
import { UserClassNameComponent } from './userfolder/user-class-name/user-class-name/user-class-name.component';
import { AttendanceUserComponent } from './userfolder/attendance-user/attendance-user/attendance-user.component';


import { FetchuserComponent } from './userfolder/fetchuser/fetchuser.component';


import { FetchnutritionComponent } from './userfolder/fetchnutrition/fetchnutrition.component';


import { ForgotpasswordComponent } from './Dashboard/forgotpassword/forgotpassword.component';
import { UserProfileComponent } from './userfolder/user-profile/user-profile.component';
import { DoctorprofileComponent } from './doctorfolder/doctorprofile/doctorprofile.component';
import { StoreComponent } from './userfolder/store/store.component';
import { BurnatleastComponent } from './userfolder/burnatleast/burnatleast.component';
import { WorkoutListComponent } from './trainerfolder/workoutlist/workout-list.component';

import { ProgressComponent } from './trainerfolder/progress/progress.component';


import { UserWorkoutsComponent } from './trainerfolder/user-workouts/user-workouts/user-workouts/user-workouts.component';
import { RegisterComponent } from './Dashboard/register/register.component';
import { UserfetchComponent } from './doctorfolder/userfetch/userfetch.component';

import { UserExerciseComponent } from './userfolder/user-exercise/user-exercise/user-exercise.component';
import { ProgressReportsComponent } from './userfolder/progress-reports/progress-reports/progress-reports.component';
import { FetchpaymentComponent } from './userfolder/fetchpayment/fetchpayment.component';
import { FetchworkoutsComponent } from './trainerfolder/fetchworkouts/fetchworkouts.component';
import { VerificationCodeComponent } from './Dashboard/verification-code/verification-code.component';
import { ForgotComponent } from './Dashboard/forgot/forgot.component';
import { DoctorregisterComponent } from './doctorregister/doctorregister.component';
import { TrainerselfregComponent } from './trainerselfreg/trainerselfreg.component';



const routes: Routes = [

  { path: '', component: LoginComponent },



  {
    path: 'admin', component: AdminnavComponent,
    children: [
      { path: 'users', component: UserListComponent },
      { path: 'users/add', component: UserFormComponent },
      { path: 'users/add/historymedical', component: MedicalHistoryComponent },
      { path: 'trainers', component: TrainerListComponent },
      { path: 'trainers/add', component: TrainerregComponent },
      { path: 'doctors', component: DoctorListComponent },
      { path: 'doctors/add', component: DoctorComponent },
      { path: 'doctors/edit/:id', component: DoctorComponent },
      { path: 'users/edit/:id', component: UserFormComponent },
      { path: 'trainers/edit/:id', component: TrainerregComponent },
      { path: 'equipment/edit/:id', component: EquipmentregeComponent },
      { path: 'membership/edit/:id', component: MembershipregComponent },


      { path: 'equipment', component: EquipmentListComponent },
      { path: 'equipment/add', component: EquipmentregeComponent },
      { path: 'membership', component: MembershipComponent },
      { path: 'membership/add', component: MembershipregComponent }
    ]

  },


  { path: 'login', component: LoginComponent },

  { path: 'about', component: AboutComponent },

  { path: 'home', component: HomeComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'userlogin', component: RegisterComponent },
  { path: 'userlogin/payments', component: PaymentComponent },
  { path: 'userlogin/historymedical', component: MedicalHistoryComponent },

  // { path: "forgot", component: ForgotpasswordComponent },



  { path: 'doctors/edit/:id', component: DoctorComponent },
  { path: 'doctors/add', component: DoctorComponent },


  { path: 'trainers/edit/:id', component: TrainerregComponent },
  { path: 'trainers/add', component: TrainerregComponent },



  {
    path: 'trainer', component: TrainerComponent,
    children: [
      { path: 'trainerpro', component: TrainerProfileComponent },
      { path: 'user-class', component: UserclassComponent },

      { path: 'user-class/classes', component: ClassschedulingComponent },
      { path: 'user-class/attendance', component: AttendanceComponent },
      { path: 'user-workouts', component: FetchworkoutsComponent },

      { path: 'user-class/classes', component: ClassschedulingComponent },
      { path: 'user-class/attendence', component: AttendanceComponent },

      { path: 'user', component: TrainerUsersComponent },
      { path: 'user/exercise', component: ExerciseComponent },
      { path: 'user/workout', component: WorkoutComponent },
      { path: 'user/progress', component: ProgressComponent },


      { path: 'user-class', component: UserclassComponent },
      { path: 'viewprofile', component: TrainerProfileComponent },

    ]
  },



  {
    path: 'doctorDashboard', component: DoctorDashboardComponent,
    children: [

      { path: 'doctorpro', component: DoctorprofileComponent },

      { path: 'patients', component: MyPatientComponent },

      { path: 'nutrition', component: NutritionTableComponent },

      { path: 'userfetch/medicalHistoryDisplay', component: MedicalhistoryFetchComponent },
      { path: 'userfetch', component: UserfetchComponent },
    
    ]

  },


  { path: 'progress', component: ProgressComponent },
  { path: '', redirectTo: '/user-list', pathMatch: 'full' },





  {

    path: 'verticalnav', component: VerticalnavComponent,

    children: [
      { path: 'workouts', component: WorkoutListComponent },
      { path: 'workouts/add', component: WorkoutComponent },
      { path: 'homeuser', component: HomenavComponent },

      { path: 'homeuser/burn', component: BurnatleastComponent },



      { path: 'homeuser/drink-water', component: DrinkwaterComponent },

      { path: 'homeuser/view-all', component: ViewalltrackersComponent },
      { path: 'plans', component: PlansComponent },
      {
        path: 'store', component: StoreComponent
      },
      { path: 'more', component: MorehomeComponent },
      { path: 'userpro', component: UserProfileComponent },

      { path: 'more/fetch-classes', component: UserClassNameComponent },
      { path: 'more/fetch-progress', component: ProgressReportsComponent },
      { path: 'more/fetch-attendance', component: AttendanceUserComponent },
      { path: 'more/fetch-nutrition', component: FetchnutritionComponent },
      { path: 'more/fetch-details', component: FetchuserComponent },

      { path: 'more/fetch-exercise', component: UserExerciseComponent },

      { path: 'more/fetch-payments', component: FetchpaymentComponent },
      {path:'usertrainers',component:TrainerListComponent},
      { path: 'userdoctors', component: DoctorListComponent },
      { path: 'userdoctors/add', component: DoctorComponent },
      { path: 'usertrainers/add', component: TrainerregComponent },



    ]

  },


  { path:'doctorRegister',component:DoctorregisterComponent},
  {
    path:'trainerRegister',component:TrainerselfregComponent
  },

  { path: '', redirectTo: '/about', pathMatch: 'full' },

  { path: 'forgot', component: VerificationCodeComponent},

  { path: 'verifyotp', component: ForgotComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],


})
export class AppRoutingModule { }
