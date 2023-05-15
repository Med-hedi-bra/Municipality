import { Component, OnInit } from '@angular/core';
import { map, throwError } from 'rxjs';
import { DemandeRow } from 'src/app/services/DTO/DemandeRow';
import { DataService } from 'src/app/services/data/data.service';

@Component({
  selector: 'app-copie',
  templateUrl: './copie.component.html',
  styleUrls: ['./copie.component.css']
})
export class CopieComponent implements OnInit {
  listDemande:DemandeRow[] = [
    // {
    //  idDemande: 200,
    //  file: "string",
    //  state: "PENDING",
    //  type: "COPIECONFORME",
    //  title: "TITLE1",
    // },
    ];
  constructor(private dataService: DataService){}
  ngOnInit(): void {
    this.dataService.getAllDemand().subscribe({
      next: res => {
        res.forEach(element => {
          this.listDemande.push({
            idDemande: element.idDemande,
            title:element.title,
            type: element.type,
            state: element.state,
          })
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
