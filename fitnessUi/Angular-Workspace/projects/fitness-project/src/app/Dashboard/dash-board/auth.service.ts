import { Router } from "@angular/router";

import { FormBuilder } from "@angular/forms";
import { Login } from "../../../Models/login.model";
import { Injectable } from "@angular/core";
import { UserService } from "../../../Services/user.service";
@Injectable({
  providedIn:'root'
})
export class AuthService{
  
  
    session:any=null;
    constructor(
       
        private service: UserService,
        private router: Router
      ) {}

      public isLogged:boolean=false;
      public user:any=null;
    loginedIn(login:Login){
        this.service.loginDetails(login).subscribe(
        (response: any) => {
         
          const json = response.JSON;
          

          if (response != null) {
            console.log("hello");
            
            if (response.role === 'USER') {
              console.log("hiii"); 
             
              this.isLogged=true;
              this.router.navigate(['/verticalnav']);
            }
           

           this.session="login";
          }
        }
      );
    

    }

    logout(){
        this.session=null;
        this.isLogged=false;
        localStorage.removeItem('user')
    }

    getUser(){
      const userData=localStorage.getItem('user')
      return userData ? JSON.parse(userData) : null;
    }

}