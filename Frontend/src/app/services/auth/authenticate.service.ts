import {  Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { find, Observable, of, throwError } from 'rxjs';
import { role } from 'src/app/model/role';
import { Token } from 'src/app/model/token.model';
import { User } from 'src/app/model/user.model';



@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFobWVkIiwicm9sZSI6IlVTRVIiLCJwYXNzd29yZCI6ImFobWVkIiwiY2luIjoiMiIsImlhdCI6MTUxNjIzOTAyMn0.udhnlUiY0IkD6oD022IoN1cjUlV_6EVZ3Ss35N41-kk"
  adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtZWQiLCJyb2xlIjoiQURNSU4iLCJwYXNzd29yZCI6ImFkbWluIiwiY2luIjoiMSIsImlhdCI6MTUxNjIzOTAyMn0.-6Vh_hCR-QKtiMsNjXe1xPuxBtk8J1x1leiYDfBce8g"
  token!:string;
  users: User[] = [];
  currentUser!: any;
  // must get the data from the back because in every page the service is reloaded

  constructor() {
    this.users.push(
      { cin: "1", username: "ahmed", password: "ahmed", role: role.USER },
      { cin: "2", username: "admin", password: "admin", role: role.ADMIN }
     
    );
    
  }

  public login(cin: string, password: string): Observable<User> {
    let findUser = this.users.find(u => u.cin == cin);
    if (findUser) {
      if (findUser.password == password) {
        
        this.token = this.adminToken;
        return of(findUser);
      }
      else return throwError(() => new Error('Bad credentiels'));
    }
    else {
      return throwError(() => new Error('User not found'));
    }
  }

  public authenticateUser(user: User): Observable<boolean> {
    
    this.currentUser = user;
    localStorage.setItem("TOKEN", this.token);
    
    return of(true);
  }

  public userRole(): string {
    if(this.currentUser){
      return this.currentUser.role;
    }
    else {
      return "";
    }
    

    // return this.authenticatedUser!.role.includes(role);
  }
  
  public isAuthenticated():boolean{
    if(this.currentUser || localStorage.getItem("TOKEN")){
      return true;
    }
    return false ;
  }
  public logout() : Observable<boolean>{
    this.currentUser = undefined;
    localStorage.removeItem("TOKEN");
    return of(true);
  }
}


