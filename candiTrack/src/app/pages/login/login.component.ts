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
      console.log(form.value["username"]);
      this.authService.login({
        username: form.value["username"],
        password: form.value["password"]
      }).subscribe({
        next: (res) => {
          console.log(res);
          localStorage.setItem('token', res.token);
          localStorage.setItem('username', form.value["username"]);
          this.errorMessage = null;
          console.log("pass3");
          this.router.navigate(['/home']);
          console.log("pass4");
        },
        error: (err) => {
          this.errorMessage = "échec de connexion";
        }
      });
    }
  }

}
