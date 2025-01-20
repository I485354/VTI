import { bootstrapApplication } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app/app.component';

import { provideRouter, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: AppComponent },

];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
  ],
}).catch(err => console.error(err));
