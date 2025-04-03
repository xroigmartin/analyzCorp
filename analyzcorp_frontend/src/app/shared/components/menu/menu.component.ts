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
            label: 'Balance',
            icon: 'pi pi-wallet',
            items:[
              {
                label: 'Expense',
                icon: 'pi pi-money-bill',
                routerLink: 'personal-economy/balance/expense'
              },
              {
                label: 'Income',
                icon: 'pi pi-dollar',
                routerLink: 'personal-economy/balance/income'
              }
            ]
          },
          {
            label: 'Budget',
            icon: 'pi pi-receipt',
            routerLink: 'personal-economy/budget'
          },
          {
            label: 'Bank Account',
            icon: 'pi pi-building-columns',
            routerLink: 'personal-economy/account'
          },
          {
            label: 'Transactions',
            icon: 'pi pi-receipt',
            routerLink: 'personal-economy/transaction'
          },
          {
            label: 'Categories',
            icon: 'pi pi-book',
            routerLink: 'personal-economy/category'
          }
        ]
      },
      {
        label: 'Control panel',
        icon: 'pi pi-sliders-h',
        items: [
          {
            label: 'Currencies',
            icon: 'pi pi-money-bill',
            routerLink: 'control-panel/currency'
          }
        ]
      }
    ]
  }
}
