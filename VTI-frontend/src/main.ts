import { bootstrapApplication } from '@angular/platform-browser';
/*import { appConfig } from './app/app.config';*/
import { routes } from './app/app.routes';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import {HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { AuthInterceptor } from './app/auth.interceptor';



bootstrapApplication(AppComponent,  {
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    importProvidersFrom(HttpClientModule),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }

  ]
}).catch(err => console.error(err));
