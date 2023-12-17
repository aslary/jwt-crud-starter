import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Globals {

  readonly backendUri: string = this.findBackendUrl();

  private findBackendUrl(): string {
    if (window.location.port === '4200') {
      return 'http://localhost:8080/api/v1';
    } else {
      return window.location.protocol + '//' + window.location.host + window.location.pathname + 'api/v1';
    }
  }

}
