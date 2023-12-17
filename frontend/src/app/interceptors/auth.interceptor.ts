import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AuthService} from '../services/auth.service';
import {Globals} from '../globals/globals';
import {catchError, Observable, throwError} from 'rxjs';
import {Router} from '@angular/router';

// There is also HttpInterceptorFn. Mixing the functional approach with the old approach works fine.
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  whiteList = [
    'login', 'register'
  ]

  constructor(private auth: AuthService, private globals: Globals, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const uris = [];
    for (const path of this.whiteList) {
      uris.push(this.globals.backendUri + '/' + path);
    }

    // Do not intercept these requests
    if (uris.includes(req.url)) {
      return next.handle(req);
    }

    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + this.auth.getToken())
    });

    return next.handle(authReq).pipe(catchError(x => this.handleAuthError(x)));
  }

  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    if (err.status === 401 || err.status === 403) {
      this.router.navigate([`/login`]);
      this.auth.logout();
    }
    console.log(err);
    return throwError(() => err);
  }

}
