import {Component} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {RegisterDto} from '../../dtos/register';
import {TranslateModule} from '@ngx-translate/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    TranslateModule,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.sass'
})
export class RegisterComponent {

  dto = new RegisterDto('', '', '', '');

  constructor(private auth: AuthService, private router: Router) {
  }

  register() {
    this.auth.register(this.dto).subscribe({
      next: value => {
        this.router.navigate(['/secret']);
      },
      error: err => {
        console.log(err)
      }
    });
  }

}
