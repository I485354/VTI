import { bootstrapApplication } from '@angular/platform-browser';
/*import { appConfig } from './app/app.config';*/
import { routes } from './app/app.routes';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';

/*bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));*/

bootstrapApplication(AppComponent,  {
  providers: [
    provideHttpClient(),
    provideRouter(routes)

  ]
}).catch(err => console.error(err));
