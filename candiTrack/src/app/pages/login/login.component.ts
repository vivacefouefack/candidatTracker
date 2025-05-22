import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  login(form: NgForm) {
    if (form.valid) {
      console.log("pass2");
      this.authService.login({
        username: this.username,
        password: this.password
      }).subscribe({
        next: (res) => {
          console.log(res);
          localStorage.setItem('token', res.token);
          this.errorMessage = null;
          // this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          this.errorMessage = "échec de connexion";
        }
      });
    }
  }

}
