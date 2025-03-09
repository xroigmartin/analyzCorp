import { Routes } from '@angular/router';
import {ListAccountsComponent} from './personal_economy/bank/account/pages/list-accounts/list-accounts.component';
import {UpdateAccountComponent} from './personal_economy/bank/account/pages/update-account/update-account.component';
import {NewAccountComponent} from './personal_economy/bank/account/pages/new-account/new-account.component';
import {
  ListCurrenciesComponent
} from './control_panel/currency/pages/list-currencies/list-currencies.component';
import {
  ListTransactionsComponent
} from './personal_economy/transaction/pages/list-transactions/list-transactions.component';
import {
  CreateTransactionComponent
} from './personal_economy/transaction/pages/create-transaction/create-transaction.component';
import {ListCategoriesComponent} from './personal_economy/category/pages/list-categories/list-categories.component';
import {CreateCategoryComponent} from './personal_economy/category/pages/create-category/create-category.component';
import {UpdateCategoryComponent} from './personal_economy/category/pages/update-category/update-category.component';

export const routes: Routes = [
  {
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
      },
      {
        path: 'transaction',
        children:[
          {
            path: '',
            component: ListTransactionsComponent,
          },
          {
            path: 'new',
            component: CreateTransactionComponent
          }
        ]
      },
      {
        path: 'category',
        children:[
          {
            path: '',
            component: ListCategoriesComponent,
          },
          {
            path: 'new',
            component: CreateCategoryComponent,
          },
          {
            path: ':id',
            component: UpdateCategoryComponent
          }
        ]
      }
    ]
  },
  {
    path: 'control-panel',
    children: [
      {
        path: 'currency',
        component: ListCurrenciesComponent
      }
    ]
  }
];
