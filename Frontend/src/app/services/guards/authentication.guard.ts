import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticateService } from '../auth/authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {
  constructor(private authService:AuthenticateService, private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(route.data["roles"].includes(this.authService.userRole()) ){ //the user must have the admin role
      
      return true;
    }
    else{
      this.router.navigateByUrl("/login");
      return false;
    }
  }
   
}


@Injectable({
  providedIn: 'root'
})
export class IsLogedIn implements CanActivate {
  constructor(private authService:AuthenticateService, private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(!this.authService.isAuthenticated()){ 
      
     return true;
    }
    else{
     this.router.navigateByUrl("/home");
     return false;
    }
  }
   
}

