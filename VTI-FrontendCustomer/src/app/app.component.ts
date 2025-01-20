import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CustomerPageComponent } from './customer-page/customer-page.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, CustomerPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'VTI-Customer';
  
}
