import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  username = '';
  password = '';
  confirmPassword = '';
  passwordMismatch = false;
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  register(form: NgForm) {
    this.passwordMismatch = form.value["password"] !== form.value["confirmPassword"];
    console.log("pass1")

    if (form.valid && !this.passwordMismatch) {
      console.log("pass2")
      console.log(form.value["username"])
      console.log(form.value["password"])
      this.authService.register({
        username: form.value["username"],
        password: form.value["password"]
      }).subscribe({
        next: () => {
          alert("Inscription réussie !");
          this.errorMessage = null;
          this.router.navigate(['/login']);
        },
        error: () => {
          this.errorMessage = "échec d'inscription. réessayez.";
        }
      });
      console.log("pass3")
    }
  }
}
