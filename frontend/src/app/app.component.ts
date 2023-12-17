import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from './components/header/header.component';
import {TranslateService} from '@ngx-translate/core';
import {defaultCode, langs} from '../assets/i18n/language';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {

  constructor(private translateService: TranslateService) {
    translateService.setDefaultLang(defaultCode);
    let code = localStorage.getItem('lang');
    if (!code || !langs.map(lang => lang.code).includes(code)) {
      code = defaultCode;
      localStorage.setItem('lang', code);
    }
    translateService.use(code);
  }

}
