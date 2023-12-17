import {Component} from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {TranslateModule} from '@ngx-translate/core';

@Component({
  selector: 'app-only-authenticated',
  standalone: true,
  imports: [
    TranslateModule
  ],
  templateUrl: './only-authenticated.component.html',
  styleUrl: './only-authenticated.component.sass'
})
export class OnlyAuthenticatedComponent {

  constructor(public auth: AuthService) {
  }

}
