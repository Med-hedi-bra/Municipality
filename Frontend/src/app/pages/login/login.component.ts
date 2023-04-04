import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { role } from 'src/app/model/role';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {    

  loginForm!: FormGroup;
  errorMsg!: string;
  constructor(private fb: FormBuilder, private authService: AuthenticateService, private router: Router ) { }
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      // cin: this.fb.control("", [Validators.required, Validators.minLength(8), Validators.maxLength(11)]),
      // password: this.fb.control("", [Validators.required])

      cin: this.fb.control("",),
      password: this.fb.control("",)
   
    })
  }
  handleLogin() {
    let cin = this.loginForm.value.cin;
    let password = this.loginForm.value.password;
    this.authService.login(cin, password).subscribe(
      {
        next: (data) => {
          this.authService.authenticateUser(data).subscribe({
            next: (data) => {
              
              if (this.authService.userRole() == role.ADMIN) {
                this.router.navigateByUrl("/admin");
              }
              else {
                this.router.navigateByUrl("/home");
              }

            },
            error: (err) => {

            }
          })
        },
        error: (err) => {
          this.errorMsg = err;
        }
      }
    )

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

