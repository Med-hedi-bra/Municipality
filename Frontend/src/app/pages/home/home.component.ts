import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';
import { RegisterRequestPayload } from '../register/register-request.payload';
import { User } from '../../model/user.model';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  registerRequestPayload!: RegisterRequestPayload;
  user!: User;


  constructor(
    private authService: AuthenticateService,
    private http: HttpClient
  ){}
  
  ngOnInit() {

    if(this.authService.isAuthenticated()){

    this.user =this.authService.currentUser;
    console.log(this.user);
    console.log(this.authService.getMun());
    }

    
   
   
   
    
    // cin: this.authService.currentUser.get('cin').value;
    // codeMun: this.authService.currentUser.get('codeMun').value;
    // console.log(cin);
   
   
   
   

   this.http.get("http://localhost:8080/municipality/user/");
   this.http.get("http://localhost:8080/municipality/user/");
  }

  
 
}
