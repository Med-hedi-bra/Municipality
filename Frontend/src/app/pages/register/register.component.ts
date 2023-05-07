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
  
  regsiterRequestPayload!: RegisterRequestPayload;
  registerForm !: FormGroup;

  
  
  constructor(private authService:AuthenticateService,
              private router: Router){
                this.regsiterRequestPayload={
                  cin: '',
                  firstname: '',
                  lastname: '',
                  gender: '',
                  dateOfBirth: '',
                  email:'',
                  password: ''

                }
              }
  
  
ngOnInit() {
  this.registerForm = new FormGroup({
    cin: new FormControl('', Validators.required),
    firstname: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    gender: new FormControl('', Validators.required),
    dateOfBirth: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });
}

register() {
  this.registerRequestPayload.cin = this.registerForm.get('').value,
  this.registerRequestPayload.firstname = this.registerForm.get('username').value,
  this.registerRequestPayload.lastname = this.registerForm.get('username').value,
  this.registerRequestPayload.gender = this.registerForm.get('username').value,
  this.registerRequestPayload.dateOfBirth = this.registerForm.get('username').value,
  this.registerRequestPayload.email = this.registerForm.get('username').value,
  this.registerRequestPayload.password = this.registerForm.get('password').value

  this.authService.register(this.registerRequestPayload)
    .subscribe(data => {
      this.router.navigate(['/login'],
        { queryParams: { registered: 'true' } });
    }, error => {
      console.log(error);
      this.toastr.error('Registration Failed! Please try again');
    });
}

}
