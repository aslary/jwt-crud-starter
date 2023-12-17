import {Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {authenticated} from './guards/auth.guard';
import {OnlyAdminComponent} from './components/authenticated/only-admin/only-admin.component';
import {OnlyAuthenticatedComponent} from './components/authenticated/only-authenticated/only-authenticated.component';
import {Role} from './model/role';

export const routes: Routes = [
  {path: '', redirectTo: '/about-me', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {
    path: 'about-me',
    component: OnlyAuthenticatedComponent,
    canActivate: [authenticated()], // Any authenticated user, regardless of role
    runGuardsAndResolvers: 'always'
  },
  {
    path: 'admin',
    component: OnlyAdminComponent,
    canActivate: [authenticated(Role.ADMIN)], // Only admins that are authenticated
    runGuardsAndResolvers: 'always'
  },
  {path: '**', redirectTo: '/'}
];
