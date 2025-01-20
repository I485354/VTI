import { Routes } from '@angular/router';
import { CustomerPageComponent } from './customer-page/customer-page.component';

export const routes: Routes = [
  { path: '', component: CustomerPageComponent },
  { path: '**', redirectTo: '' } // Om te voorkomen dat ongeldige routes een fout opleveren
];
