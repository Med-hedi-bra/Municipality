import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RegisterRequestPayload } from './register-request.payload';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  registerRequestPayload!: RegisterRequestPayload;
  registerForm!: FormGroup;

  
  
  constructor(private authService:AuthenticateService,
              private router: Router,
              ){
                // this.registerRequestPayload={
                //   cin: '',
                //   mun: 0,
                //   firstname: '',
                //   lastname: '',
                //   gender: '',
                //   dateOfBirth: '' ,
                //   email:'',
                //   password: ''

                // };
              }
  
  
ngOnInit() {
  
  this.registerForm = new FormGroup({
    cin: new FormControl('', Validators.required),
    codeMun: new FormControl('', Validators.required),
    firstname: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    gender: new FormControl('', Validators.required),
    dateOfBirth: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required)
  });
}

handleRegister() {
  
  this .registerRequestPayload = {
    cin: this.registerForm.get('cin')?.value,
    codeMun: this.registerForm.get('codeMun')?.value,
    firstname: this.registerForm.get('firstname')?.value,
    lastname: this.registerForm.get('lastname')?.value,
    gender: this.registerForm.get('gender')?.value,
    dateOfBirth: this.registerForm.get('dateOfBirth')?.value,
    password: this.registerForm.get('password')?.value,
    email: this.registerForm.get('email')?.value
  
  }

console.log(this.registerRequestPayload);

  this.authService.signup(this.registerRequestPayload)
    .subscribe({
     next: data => {
        this.router.navigate(['/login'],
          { queryParams: { registered: 'true' } });
      }, 
      error: error => {
        console.log(error);
        //this.toastr.error('Registration Failed! Please try again');
      }
    });
 }

}
