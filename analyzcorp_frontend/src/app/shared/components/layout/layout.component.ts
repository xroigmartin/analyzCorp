import { Component } from '@angular/core';
import {RouterModule} from '@angular/router';
import {MenuComponent} from '../menu/menu.component';
import {MessageComponent} from '../message/message.component';

@Component({
  standalone: true,
  selector: 'shared-layout',
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
  imports: [RouterModule, MenuComponent]
})
export class LayoutComponent {

}
