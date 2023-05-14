import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticateService } from 'src/app/services/auth/authenticate.service';

@Component({
  selector: 'app-demande',
  templateUrl: './legalisation.component.html',
  styleUrls: ['./legalisation.component.css']
})
export class LegalisationComponent implements OnInit {

  demandeForm!: FormGroup;

  constructor(private authService:AuthenticateService,
    private router: Router,
    ){
      
    }


  ngOnInit() {
    this.demandeForm = new FormGroup({
      cin: new FormControl('', Validators.required),
      demfile: new FormControl('', Validators.required),
      
    });
  }


}
