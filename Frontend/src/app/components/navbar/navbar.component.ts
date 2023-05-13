import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(private authService : AuthenticateService, private router:Router){}
  ngOnInit(): void {
     }

  handleLogout(){
    this.authService.logout().subscribe({
      next:(data)=>{
        console.log("dsfsdfs");
        this.router.navigateByUrl("/login");
      }
    });
  }
  displayLogout():boolean{ 
    return this.authService.isAuthenticated();
  }
  // displayAdmin():boolean{
  //   if(this.authService.currentUser){
  //     return false;
  //   }
  //   return false;
  // }
}
