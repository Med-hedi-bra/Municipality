import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  constructor(private http: HttpClient) {

  }
  send() {
    console.log("hhhh");
    this.http.get<any>('http://localhost:8080/apiStuff/').subscribe({
      next: (data) => {
        console.log(data);
      }
    }

    )
  };


}
