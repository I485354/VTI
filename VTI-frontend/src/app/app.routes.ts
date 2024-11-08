import { Routes } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InvoicesComponent } from './invoices/invoices.component';
import { QuotesComponent } from './quotes/quotes.component';
import { PaymentsComponent } from './payments/payments.component';
import { SettingsComponent } from './settings/settings.component';
import { NewInvoiceComponent } from './new-invoice/new-invoice.component';
import { ProductFormComponent } from './product-form/product-form.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'invoices', component: InvoicesComponent },
  { path: 'new-invoice', component: NewInvoiceComponent },
  { path: 'quotes', component: QuotesComponent },
  { path: 'product-form', component: ProductFormComponent },
  { path: 'customer-list', component: CustomerListComponent },
  { path: 'payments', component: PaymentsComponent }, 
  { path: 'settings', component: SettingsComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '', redirectTo: '/login', pathMatch: 'full' }// De route naar je klantenlijst
];
