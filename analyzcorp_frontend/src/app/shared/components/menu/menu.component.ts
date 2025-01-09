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
        label: 'Features',
        icon: 'pi pi-star'
      },
      {
        label: 'Projects',
        icon: 'pi pi-search',
        items: [
          {
            label: 'Components',
            icon: 'pi pi-bolt'
          },
          {
            label: 'Blocks',
            icon: 'pi pi-server'
          },
          {
            label: 'UI Kit',
            icon: 'pi pi-pencil'
          },
          {
            label: 'Templates',
            icon: 'pi pi-palette',
            items: [
              {
                label: 'Apollo',
                icon: 'pi pi-palette'
              },
              {
                label: 'Ultima',
                icon: 'pi pi-palette'
              }
            ]
          }
        ]
      },
      {
        label: 'Contact',
        icon: 'pi pi-envelope'
      }
    ]
  }
}
