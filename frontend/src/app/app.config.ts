import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter, withRouterConfig} from '@angular/router';

import {routes} from './app.routes';
import {
  HTTP_INTERCEPTORS,
  HttpBackend,
  HttpClient,
  provideHttpClient,
  withInterceptorsFromDi
} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AcceptLanguageInterceptor} from './interceptors/accept-language.interceptor';
import {AuthInterceptor} from './interceptors/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withRouterConfig({onSameUrlNavigation: 'reload'})),
    provideHttpClient(withInterceptorsFromDi()), // utilizes the old way of using interceptors, can be mixed with functions too
    importProvidersFrom(
      TranslateModule.forRoot({
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpBackend],
        }
      })
    ),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AcceptLanguageInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
};

export function HttpLoaderFactory(http: HttpBackend) {
  return new TranslateHttpLoader(new HttpClient(http));
}
