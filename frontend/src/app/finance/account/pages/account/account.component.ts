import { Component } from '@angular/core';
import {TranslatePipe} from '@ngx-translate/core';
import {NgForOf} from '@angular/common';

interface Account {
  id: number;
  name: string;
}

@Component({
  selector: 'app-account',
  imports: [
    TranslatePipe,
    NgForOf
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent {
  public accounts: Account[] = [
    { id: 1, name: 'Cuenta principal' },
    { id: 2, name: 'Ahorros' }
  ];
}
