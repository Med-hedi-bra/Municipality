import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticateService } from '../auth/authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authService:AuthenticateService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.authService.token || localStorage.getItem("TOKEN");
    
    if(authToken){
        const authReq = req.clone({
          headers:req.headers.set("Authorization",`Bearer ${authToken}`)
        })
        return next.handle(authReq);
    }
    else{
      return next.handle(req)
    }
  }
}
