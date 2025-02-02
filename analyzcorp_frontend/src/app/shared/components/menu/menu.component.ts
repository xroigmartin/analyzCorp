import {Component, OnInit} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem} from 'primeng/api';

@Component({
  standalone: true,
  selector: 'shared-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
  imports: [Menubar]
})
export class MenuComponent implements OnInit{

  items: MenuItem[] | undefined;

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-home'
      },
      {
        label: 'Personal economy',
        icon: 'pi pi-money-bill',
        items: [
          {
            label: 'Bank Account',
            icon: 'pi pi-building-columns',
            routerLink: 'personal-economy/account'
          }
        ]
      },
      {
        label: 'Configuration',
        icon: 'pi pi-cog',
        items: [
          {
            label: 'Currencies',
            icon: 'pi pi-money-bill',
            routerLink: 'config/currency'
          }
        ]
      }
    ]
  }
}
