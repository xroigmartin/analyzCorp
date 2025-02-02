import { Routes } from '@angular/router';
import {ListAccountsComponent} from './personal_economy/bank/account/pages/list-accounts/list-accounts.component';
import {UpdateAccountComponent} from './personal_economy/bank/account/pages/update-account/update-account.component';
import {NewAccountComponent} from './personal_economy/bank/account/pages/new-account/new-account.component';

export const routes: Routes = [{
  path: 'personal-economy',
  children: [
    {
      path: 'account',
      children:[
        {
          path: '',
          component: ListAccountsComponent,
        },
        {
          path: 'new',
          component: NewAccountComponent
        },
        {
          path: ':id',
          component: UpdateAccountComponent
        }
      ]
    }
  ]
}];
