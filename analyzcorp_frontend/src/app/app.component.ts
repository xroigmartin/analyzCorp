import { Component } from '@angular/core';
import {LayoutComponent} from './shared/components/layout/layout.component';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [LayoutComponent]
})
export class AppComponent {
  title = 'analyzcorp_frontend';
}
