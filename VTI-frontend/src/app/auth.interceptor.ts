import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpHeaders
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept<T>(req: HttpRequest<T>, next: HttpHandler): Observable<HttpEvent<T>> {
    console.log('Intercepting URL:', req.url);
    // 1) Kijk of het om login/register gaat:
    //    Die wil je meestal NIET onderscheppen (want je hebt nog geen token).
    //    Heb je custom paths, pas dit filter gerust aan:
    if (req.url.includes('/user/login') || req.url.includes('/user/register')) {
      return next.handle(req); // Ga direct door, geen Authorization-header
    }

    // 2) Haal de token op uit localStorage
    const token = localStorage.getItem('token');
    if (token) {
      // 3) Clone de request met de Authorization‐header
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Authorization':`${token}`
        }),

      });

      // 4) Handel de request af en afvangen van evt. 401/403 errors
      return next.handle(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            // Token niet geldig of verlopen → eventueel uitloggen
            localStorage.removeItem('token');
            this.router.navigate(['/login']);
          }
          // Hergooi de fout
          return throwError(() => error);
        })
      );
    }

    // Als geen token aanwezig is, gewoon doorgaan:
    return next.handle(req);
  }
}
