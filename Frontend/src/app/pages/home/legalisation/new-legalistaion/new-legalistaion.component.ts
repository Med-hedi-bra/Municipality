import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DemandeRequest } from 'src/app/services/DTO/DemandRequest';
import { DemandeRow } from 'src/app/services/DTO/DemandeRow';
import { DataService } from 'src/app/services/data/data.service';
@Component({
  selector: 'app-new-legalistaion',
  templateUrl: './new-legalistaion.component.html',
  styleUrls: ['./new-legalistaion.component.css']
})
export class NewLegalistaionComponent implements OnInit{





  newDemandForm!: FormGroup;
  demand!:DemandeRow;
  test!:boolean
  constructor(private dataService:DataService){}
  ngOnInit(): void {
    this.newDemandForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description :  new FormControl('', Validators.required),
      file: new FormControl('', Validators.required),
    });
  }
  
submit(){
 this.demand = {
  type:'LEGALISATION',
  title:this.newDemandForm.get('title')?.value
 }
 this.dataService.addNewDemand(this.demand)
 this.test = true;
 this.newDemandForm.reset()

}
  
}


