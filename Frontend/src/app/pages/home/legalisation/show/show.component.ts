import { Component } from '@angular/core';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent {
  // pdfUrl = "https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";
  pdfUrl = 'https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf';
  downloadFile() {
    const link = document.createElement('a');
    link.href = this.pdfUrl;
    link.download = 'file.pdf';
    link.click();
  }
}
