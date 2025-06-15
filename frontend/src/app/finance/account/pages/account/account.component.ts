import {Component, inject, OnInit} from '@angular/core';
import {TranslatePipe} from '@ngx-translate/core';
import {NgForOf} from '@angular/common';
import {Account} from '../../model/account';
import {AccountsService} from '../../services/accounts.service';

@Component({
  selector: 'app-account',
  imports: [
    TranslatePipe,
    NgForOf
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit {

  private readonly accountService: AccountsService = inject(AccountsService);

  accounts: Account[] = [];

  ngOnInit(): void {
    this.loadCompanies();
  }

  private loadCompanies() {
    this.accountService.getAccounts().subscribe({
      next: (accounts) => {
        this.accounts = accounts;
      },
      error: (error) => {
        console.error('Error loading accounts:', error);
      }
    });
  }
}
