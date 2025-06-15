import {NgFor, NgIf} from '@angular/common';
import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
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
export class MenuComponent implements OnInit{
  private readonly translate: TranslateService = inject(TranslateService);
  private readonly router: Router = inject(Router);

  @Input() isMobile: boolean = false;
  @Output() closeMenu: EventEmitter<void> = new EventEmitter<void>();

  menuItems: MenuItem[] = [
    {
      label: 'MENU.HOME',
      icon: 'pi pi-home',
      route: '/'
    },
    {
      label: 'MENU.FINANCE',
      icon: 'pi pi-wallet',
      expanded: false,
      children: [
        { label: 'MENU.FINANCE_ITEMS.BUDGET', icon: 'pi pi-book', route: '/budgets' },
        { label: 'MENU.FINANCE_ITEMS.TRANSACTIONS', icon: 'pi pi-list', route: '/transactions' },
        { label: 'MENU.FINANCE_ITEMS.ACCOUNT', icon: 'pi pi-credit-card', route: '/finance/accounts' }
      ]
    },
    {
      label: 'MENU.INVESTMENT',
      icon: 'pi pi-chart-line',
      expanded: false,
      children: [
        { label: 'MENU.INVESTMENT_ITEMS.COMPANIES', icon: 'pi pi-building', route: '/companies' },
        { label: 'MENU.INVESTMENT_ITEMS.FUNDAMENTAL_ANALYZE', icon: 'pi pi-search', route: '/fundamentals' }
      ]
    }
  ];

  ngOnInit(): void {
    this.expandMenusForCurrentRoute();
  }

  toggleExpand(item: MenuItem): void {
    item.expanded = !item.expanded;
  }

  onNavigate(): void {
    this.expandMenusForCurrentRoute()
    if(this.isMobile) {
      this.closeMenu.emit();
    }
  }

  private expandMenusForCurrentRoute(): void {
    const currentUrl: string = this.router.url;

    for( const item of this.menuItems ) {
      if(item.children?.some((child: MenuItem): boolean => currentUrl === child.route)) {
        item.expanded = true;
      }
      else{
        item.expanded = false;
      }
    }
  }
}
