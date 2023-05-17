import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { DemandeRow } from 'src/app/services/DTO/DemandeRow';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';
import { DataService } from 'src/app/services/data/data.service';

@Component({
  selector: 'app-demande',
  templateUrl: './legalisation.component.html',
  styleUrls: ['./legalisation.component.css']
})
export class LegalisationComponent implements OnInit {

  demandeForm!: FormGroup;

  constructor(private authService:AuthenticateService,
    private router: Router,
    private dataService:DataService
    ){
      
    }

    listDemande:DemandeRow[] = [
      // {
      //  idDemande: 200,
      //  file: "string",
      //  state: "PENDING",
      //  type: "COPIECONFORME",
      //  title: "TITLE1",
      // },
      ];
  ngOnInit() {
    this.dataService.getAllDemand().subscribe({
      next: res => {
        res.forEach(element => {
          if(element.type == 'LEGALISATION'){
            this.listDemande.push({
            idDemande: element.idDemande,
            title:element.title,
            type: element.type,
            state: element.state,
          })
          }
          
        }
        );
        console.log("demand data fetched");
      },
      error: err => {
        throwError(() => new Error(err))
      }
    }
    
    )
    console.log(this.listDemande);
  }
  deleteOne(demand:DemandeRow){
    let  index = this.listDemande.indexOf(demand);
    if (index !== -1) {
      this.listDemande.splice(index, 1);
    }
    this.dataService.deleteDemand(demand.idDemande!)
  }

}
