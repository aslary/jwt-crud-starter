import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Globals} from '../globals/globals';
import {LoginDto} from '../dtos/login';
import {jwtDecode} from 'jwt-decode';
import {RegisterDto} from '../dtos/register';
import {Role} from '../model/role';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  get expiry(): Date {
    const exp = this.getClaim('exp');

    if (exp) {
      let date = new Date(0);
      date.setUTCSeconds(exp);
      return date;
    } else {
      return new Date(0);
    }
  }

  get role(): Role {
    const role = this.getClaim('role') || '';
    if (role.includes(Role.ADMIN)) {
      return Role.ADMIN;
    } else if (role.includes(Role.USER)) {
      return Role.USER;
    }

    return Role.UNKNOWN;
  }

  get username(): string {
    return this.getClaim('sub') || '?';
  }

  get firstName(): string {
    return this.getClaim('firstName') || '?';
  }

  get lastName(): string {
    return this.getClaim('lastName') || '?';
  }

  get authenticated() {
    return this.expiry.valueOf() > new Date().valueOf();
  }

  login(loginDto: LoginDto): Observable<string> {
    return this.httpClient.post(this.globals.backendUri + '/login', loginDto, {responseType: 'text'})
      .pipe(
        tap((authResponse: string) => this.setToken(authResponse))
      );
  }

  register(registerDto: RegisterDto): Observable<string> {
    return this.httpClient.post(this.globals.backendUri + '/register', registerDto, {responseType: 'text'})
      .pipe(
        tap((authResponse: string) => this.setToken(authResponse))
      );
  }

  logout() {
    localStorage.removeItem('authToken');
  }

  getToken() {
    return localStorage.getItem('authToken') || '';
  }

  private setToken(authResponse: string) {
    localStorage.setItem('authToken', authResponse);
  }

  private getClaim(claim: string) {
    try {
      const decoded: any = jwtDecode(this.getToken());
      if (Object.hasOwn(decoded, claim)) {
        return decoded[claim];
      }
    } catch (e) {
    }
    return null;
  }

}
