import { bootstrapApplication } from '@angular/platform-browser';
import { routes } from './app/app.routes';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';



bootstrapApplication(AppComponent,  {
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    importProvidersFrom(HttpClientModule),
  ]
}).catch(err => console.error(err));
