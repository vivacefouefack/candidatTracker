import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  template: '<router-outlet></router-outlet>',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'candiTrack';
}
