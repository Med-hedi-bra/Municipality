import {  Injectable,Output, EventEmitter } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';


import { find, map, Observable, of, throwError } from 'rxjs';
import { role } from 'src/app/model/role';
import { Token } from 'src/app/model/token.model';
import { User } from 'src/app/model/user.model';
import { RegisterRequestPayload } from 'src/app/pages/register/register-request.payload';
import { LoginRequestPayload } from 'src/app/pages/login/login-request.payload';
import { LoginResponse } from 'src/app/pages/login/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';



@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  
  
  // userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFobWVkIiwicm9sZSI6IlVTRVIiLCJwYXNzd29yZCI6ImFobWVkIiwiY2luIjoiMiIsImlhdCI6MTUxNjIzOTAyMn0.udhnlUiY0IkD6oD022IoN1cjUlV_6EVZ3Ss35N41-kk"
  // adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im1vaGFtZWQiLCJyb2xlIjoiQURNSU4iLCJwYXNzd29yZCI6ImFkbWluIiwiY2luIjoiMSIsImlhdCI6MTUxNjIzOTAyMn0.-6Vh_hCR-QKtiMsNjXe1xPuxBtk8J1x1leiYDfBce8g"
  token!:string;
  // users: User[] = [];
   currentUser!: User;
   user!: User;
  // must get the data from the back because in every page the service is reloaded

  constructor(
    private httpClient: HttpClient,
    private localStorage: LocalStorageService) {}

  signup(registerRequestPayload: RegisterRequestPayload): Observable<any> {
    return this.httpClient.post('http://localhost:8080/municipality/auth/register', registerRequestPayload, { responseType: 'text' });
  }


  
  
 login(loginRequestPayload: LoginRequestPayload): Observable<LoginResponse> 
  {
   return this.httpClient.post<any>('http://localhost:8080/municipality/auth/login',
      loginRequestPayload)
      
      // .subscribe({
      //   next: data =>{
      //     console.log(data.Token);
      //     console.log(data.user);
      //     return data

      //   },
      //   error:err =>throwError(( () => new Error("adfaf");
      //   ));
        
      // })
      
      // .pipe(
      //   map((data: LoginResponse) => {
      //     this.localStorage.store('Token', data.Token);
      //     this.localStorage.store('user', data.user);

      //     this.token = data.Token;
      //     this.user = data.user;

      //     return true; // return a value to indicate success
      //   })
      // )
        

  }
  saveLoginResponse(response: LoginResponse): void {
    // console.log(response);
    localStorage.setItem('TOKEN', response.token);
    console.log(response.token);
    localStorage.setItem('USER', JSON.stringify(response.user));
    
  }

  getToken(): string {
    return this.localStorage.retrieve('token');
  }
  getUser(): RegisterRequestPayload {
    return this.localStorage.retrieve('user');
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  
  
  public authenticateUser(loginResp: LoginResponse): Observable<any> {
    
    this.currentUser = loginResp.user;
    this.token =loginResp.token;
    //return this.currentUser;
    
    
    return of(true);
  }

  // public userRole(): string {
  //   if(this.currentUser){
  //     return this.currentUser.role;
  //   }
  //   else {
  //     return "";
  //   }
    

  //   // return this.authenticatedUser!.role.includes(role);
  // }

  // getJwtToken() {
  //   return this.localStorage.retrieve('TOKEN');
  // }

  // isLoggedIn(): boolean {
  //   return this.getJwtToken() != null;
  // }
  
  public isAuthenticated():boolean{
    if(localStorage.getItem("TOKEN")){
      return true;
    }
    return false ;
  }
  
  public logout() : Observable<boolean>{
    this.currentUser = {
      cin: '',
    codeMun: 0,
    firstname: '',
    lastname: '',
    gender: '',
    dateOfBirth: new Date("12/12/2000"),
    password: '',
    email: '',
    };
    localStorage.removeItem("USER")
    localStorage.removeItem("TOKEN");
    return of(true);
  }

  public getMun(){
    return this.currentUser.codeMun;
  }
  public getCin(){
    return this.currentUser.cin;
  }
  

}


