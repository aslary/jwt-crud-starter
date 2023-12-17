import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Role} from '../model/role';

// There is also CanActivate, which is the old approach. Old and new approach can be mixed.
export function authenticated(...roles: Role[]): CanActivateFn {
  return (ars: ActivatedRouteSnapshot, rss: RouterStateSnapshot) => {
    const auth = inject(AuthService);
    const router = inject(Router);

    let additionalCondition = true;
    if (roles.length > 0) {
      additionalCondition = roles.includes(auth.role);
    }

    if (auth.authenticated && additionalCondition) {
      return true;
    } else {
      router.navigate(['/login']);
      return false;
    }
  }
}
