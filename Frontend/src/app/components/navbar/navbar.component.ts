import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';
import {role} from "src/app/model/role";

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
        this.router.navigateByUrl("/login");
      }
    });
  }
  displayLogout():boolean{ 
    return this.authService.isAuthenticated();
  }
  displayAdmin():boolean{
    if(this.authService.currentUser){
      return this.authService.currentUser.role == role.ADMIN;
    }
    return false;
  }
}
