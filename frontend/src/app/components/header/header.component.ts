import {Component} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {TranslateModule, TranslateService} from '@ngx-translate/core';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {langs} from '../../../assets/i18n/language';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    TranslateModule,
    NgForOf,
    FormsModule,
    NgIf
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.sass'
})
export class HeaderComponent {

  protected readonly langs = langs;

  constructor(public translateService: TranslateService, public auth: AuthService, private router: Router) {
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['']); // re-runs guards, handles redirect automatically thanks to guard's impl
  }

  languageChange(val: string) {
    this.use(val);
  }

  private use(code: string) {
    this.translateService.use(code);
    localStorage.setItem('lang', code);
  }
}
