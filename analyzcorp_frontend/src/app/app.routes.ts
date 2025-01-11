import { Routes } from '@angular/router';
import {ListAccountsComponent} from './personal_economy/bank/pages/list-accounts/list-accounts.component';
import {NewAccountComponent} from './personal_economy/bank/pages/new-account/new-account.component';

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
        }
      ]
    }
  ]
}];
