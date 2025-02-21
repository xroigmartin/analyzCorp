import {Component, inject, OnInit} from '@angular/core';
import {AccountService} from '../../../bank/account/services/account.service';
import {TransactionService} from '../../services/transaction.service';
import {AccountDTO} from '../../../bank/account/interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {Select, SelectChangeEvent} from 'primeng/select';
import {DataView} from 'primeng/dataview';
import {TableModule} from 'primeng/table';
import {TransactionDTO} from '../../interfaces/transactionDTO.interface';
import {CurrencyPipe, DatePipe} from '@angular/common';
import {Button} from 'primeng/button';
import {Dialog} from 'primeng/dialog';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-list-transactions',
  imports: [
    Select,
    TableModule,
    DatePipe,
    CurrencyPipe,
    Button,
    RouterLink
  ],
  templateUrl: './list-transactions.component.html',
  standalone: true,
  styleUrl: './list-transactions.component.css'
})
export class ListTransactionsComponent implements OnInit{

  private readonly accountService: AccountService = inject(AccountService);
  private readonly transactionService: TransactionService = inject(TransactionService);

  accounts: AccountDTO[] = [];
  accountSelected: AccountDTO | null = null;
  transactions: TransactionDTO[] = [];
  visibleImportTransactionDialog: boolean = false;

  ngOnInit(): void {
    this.accountService.findAllBankAccounts().subscribe({
      next: (apiResponse: ApiResponse<AccountDTO[]>): void => {
        this.accounts = apiResponse.data;
        this.accountSelected = apiResponse.data[0];
        this.loadTransactions(this.accountSelected.id);
      }
    });
  }

  loadTransactions(accountId: number): void {
    this.transactionService.findAllTransactionByAccountId(accountId).subscribe({
      next: (apiResponse: ApiResponse<TransactionDTO[]>): void => {
        this.transactions = apiResponse.data;
      }
    });
  }

  selectAccount(event: SelectChangeEvent): void {
    this.accountSelected = event.value;
    this.loadTransactions(this.accountSelected!.id);
  }

  currencyClass(amount: string): string {
    if (!amount || isNaN(parseFloat(amount))) {
      return "";
    }

    return parseFloat(amount) < 0 ? "expense" : "";
  }

  showImportTransactionDialog() {
    this.visibleImportTransactionDialog = true;
  }
}
