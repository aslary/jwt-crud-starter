import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class AcceptLanguageInterceptor implements HttpInterceptor {

  constructor(private translateService: TranslateService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    try {
      const clonedRequest = req.clone(
        {headers: req.headers.append('Accept-Language', this.translateService.currentLang)}
      );
      return next.handle(clonedRequest);
    } catch {
      return next.handle(req);
    }
  }

}
