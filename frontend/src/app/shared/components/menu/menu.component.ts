import {NgFor, NgIf} from '@angular/common';
import {Component, inject, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {TranslatePipe, TranslateService} from '@ngx-translate/core';

export interface MenuItem {
  label: string;
  icon?: string;
  route?: string;
  children?: MenuItem[];
  expanded?: boolean;
}

@Component({
  selector: 'app-shared-menu',
  imports: [RouterLink, NgFor, NgIf, RouterLinkActive, TranslatePipe],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  private readonly translate: TranslateService = inject(TranslateService);

  menuItems: MenuItem[] = [
    {
      label: 'MENU.HOME',
      icon: 'pi pi-home',
      route: '/'
    },
    {
      label: 'MENU.FINANCE',
      icon: 'pi pi-wallet',
      expanded: true,
      children: [
        { label: 'MENU.FINANCE_ITEMS.BUDGET', icon: 'pi pi-book', route: '/budgets' },
        { label: 'MENU.FINANCE_ITEMS.TRANSACTIONS', icon: 'pi pi-list', route: '/transactions' },
        { label: 'MENU.FINANCE_ITEMS.ACCOUNT', icon: 'pi pi-credit-card', route: '/accounts' }
      ]
    },
    {
      label: 'MENU.INVESTMENT',
      icon: 'pi pi-chart-line',
      expanded: true,
      children: [
        { label: 'MENU.INVESTMENT_ITEMS.COMPANIES', icon: 'pi pi-building', route: '/companies' },
        { label: 'MENU.INVESTMENT_ITEMS.FUNDAMENTAL_ANALYZE', icon: 'pi pi-search', route: '/fundamentals' }
      ]
    }
  ];

  toggleExpand(item: MenuItem) {
    item.expanded = !item.expanded;
  }
}
