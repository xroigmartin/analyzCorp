import { Routes } from '@angular/router';
import { LayoutComponent } from './shared/pages/layout/layout.component';

export const routes: Routes = [
    {
      path: '',
      component: LayoutComponent,
      children: [
        {
          path: 'finance',
          children: [
            {
              path: 'accounts',
              loadComponent: () =>
                import('./finance/account/pages/account/account.component').then(m => m.AccountComponent)
            }
          ]
        }
      ]
    },

];
