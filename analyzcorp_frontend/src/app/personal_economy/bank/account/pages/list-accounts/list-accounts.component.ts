import {Component, inject, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {AccountDTO} from '../../interfaces/AccountDTO.interfaces';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';
import {BankAccountService} from '../../services/bank-account.service';
import {ApiResponse} from '../../../../../shared/interfaces/ApiResponse.interface';

@Component({
  standalone: true,
  selector: 'personal-economy-list-accounts',
  imports: [TableModule, Button, RouterLink],
  templateUrl: './list-accounts.component.html',
  styleUrl: './list-accounts.component.css'
})
export class ListAccountsComponent implements OnInit{

  private bankAccountService: BankAccountService = inject(BankAccountService);

  accounts: AccountDTO[] = [];

  ngOnInit(): void {
    this.bankAccountService.findAllBankAccounts().subscribe({
      next: (apiResponse: ApiResponse<AccountDTO[]>) => {
        this.accounts = apiResponse.data;
      }
    });
  }
}
