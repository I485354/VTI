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
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'invoices', component: InvoicesComponent, canActivate: [AuthGuard] },
  { path: 'new-invoice', component: NewInvoiceComponent, canActivate: [AuthGuard]},
  { path: 'quotes', component: QuotesComponent, canActivate: [AuthGuard] },
  { path: 'product-form', component: ProductFormComponent, canActivate: [AuthGuard] },
  { path: 'customer-list', component: CustomerListComponent, canActivate: [AuthGuard] },
  { path: 'payments', component: PaymentsComponent, canActivate: [AuthGuard] },
  { path: 'settings', component: SettingsComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
