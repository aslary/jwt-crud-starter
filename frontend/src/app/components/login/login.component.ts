import {Component} from '@angular/core';
import {TranslateModule} from '@ngx-translate/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {LoginDto} from '../../dtos/login';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    TranslateModule,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.sass'
})
export class LoginComponent {

  dto = new LoginDto('', '');

  constructor(private auth: AuthService, private router: Router) {
  }

  login() {
    this.auth.login(this.dto).subscribe({
      next: value => {
        this.router.navigate(['/secret']);
      },
      error: err => {
        console.log(err)
      }
    });
  }

}
