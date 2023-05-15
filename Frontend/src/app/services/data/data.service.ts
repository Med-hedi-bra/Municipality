import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DemandeRow } from '../DTO/DemandeRow';
import { AuthenticateService } from '../auth/authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http : HttpClient, private authService:AuthenticateService) { }

  getAllDemand(){
    let cin = this.authService.currentUser.cin;
    return this.http.get<DemandeRow[]>('http://localhost:8080/demande/get/user/'+cin);
  }

  deleteDemand(id:number){
    this.http.delete("http://localhost:8080/demande/delete/"+id).subscribe({
      next:data=>{
        console.log(data)
      },
      error:err=>{
        console.log(err)
      }
    });
  }
 addNewDemand(demand:DemandeRow){
  let cin = this.authService.currentUser.cin;
  this.http.post("http://localhost:8080/demande/add/user/"+cin,demand).subscribe({
      next:data=>{
        console.log(data)
      },
      error:err=>{
        console.log(err)
      }
    });
 }
}
