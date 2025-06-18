import {Component, inject, OnInit} from '@angular/core';
import {TranslatePipe} from '@ngx-translate/core';
import {NgForOf, NgIf} from '@angular/common';
import {Account} from '../../model/account';
import {AccountsService} from '../../services/accounts.service';
import {CardComponent} from '../../../../shared/components/card/card.component';
import {GenericModalComponent} from '../../../../shared/components/generic-modal/generic-modal.component';

@Component({
  selector: 'app-account',
  imports: [
    TranslatePipe,
    NgForOf,
    CardComponent,
    GenericModalComponent,
    NgIf
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit {

  private readonly accountService: AccountsService = inject(AccountsService);

  accounts: Account[] = [];
  showEditModal: boolean = false;

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

  onEditAccount(accountId: number): void {
    console.log("Edit Account");
    this.showEditModal = true;
  }

  onSaveAccount(): void {
    console.log("Save Account");
    this.showEditModal = false;
  }

  onDeleteAccount(accountId: number): void {
    console.log("Deleting Account");
  }
}
