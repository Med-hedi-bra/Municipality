import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { role } from 'src/app/model/role';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';
import { LoginRequestPayload } from './login-request.payload';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {    

  loginForm!: FormGroup;
  loginRequestPayload!: LoginRequestPayload;
  errorMsg!: string;
  constructor(private fb: FormBuilder, private authService: AuthenticateService, private router: Router ) { }
  
  
  ngOnInit()  {
    this.loginForm = new FormGroup({
      cin: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      
    });
    }
    
    //                    this.fb.group({
    //   // cin: this.fb.control("", [Validators.required, Validators.minLength(8), Validators.maxLength(11)]),
    //   // password: this.fb.control("", [Validators.required])

    //   cin: this.fb.control("",),
    //   password: this.fb.control("",)
   
    // })
  
  handleLogin() {
    
    this.loginRequestPayload = {
      cin: this.loginForm.get('cin')?.value,
      password: this.loginForm.get('password')?.value,
    }
    
    this.authService.login(this.loginRequestPayload).subscribe({
        next: data => {
          // this.authService.authenticateUser(data).subscribe({
          //   next: (data) => {
          //     this.router.navigateByUrl("/home");
          //     console.log(data);
          //   },
          //   error: (err) => {

          //   }
          if(data) {
            this.authService.authenticateUser(data);
            
      
            this.authService.saveLoginResponse(data)
            this.router.navigateByUrl('/');
          } else {
            console.log('Login failed!');
          }
        }}  




        );
  
  
          
          
        
        
        // },
        
        
        //   error: error => {
        //   console.log(error);
        // }

      
  
      }

  getErrorMessage(fieldName: string, err: ValidationErrors): string {
    if (err["required"]) {
      return fieldName + " est requis.";

    }
   else if (err["maxlength"] ) {
      return fieldName + " doit avoir au plus " + err["maxlength"]["requiredLength"] + " caractére ";

    }
    else if (err["minlength"] ) {
      return fieldName + " doit avoir au moins " + err["minlength"]["requiredLength"] + " caractére ";

    }
    else return "";
  }

}

