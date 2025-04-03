import {Component, inject, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {Card} from 'primeng/card';
import {BudgetService} from '../services/budget.service';
import {BudgetDTO} from '../interfaces/budgetDTO.interfaces';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {
  YearDatePickerComponent
} from '../../../shared/components/date_picker/year-date-picker/year-date-picker.component';
import {
  MonthDataPickerComponent
} from '../../../shared/components/date_picker/month-data-picker/month-data-picker.component';
import {TransactionDTO} from '../../transaction/interfaces/transactionDTO.interface';
import {TransactionService} from '../../transaction/services/transaction.service';
import {AccountSelectorComponent} from '../../../shared/components/select/account-selector/account-selector.component';
import {AccountDTO} from '../../bank/account/interfaces/AccountDTO.interfaces';
import {TransactionTypeEnum} from '../../transaction/enums/TransactionTypeEnum.interface';
import {AccountService} from '../../bank/account/services/account.service';
import {AccountStateService} from '../../../shared/services/account-state.service';
import {CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-budget',
  imports: [
    TableModule,
    Card,
    YearDatePickerComponent,
    MonthDataPickerComponent,
    AccountSelectorComponent,
    CurrencyPipe
  ],
  templateUrl: './budget.component.html',
  standalone: true,
  styleUrl: './budget.component.css'
})
export class BudgetComponent implements OnInit{

  private budgetService: BudgetService = inject(BudgetService);
  private transactionService: TransactionService = inject(TransactionService);
  private accountStateService: AccountStateService = inject(AccountStateService);

  private yearBudgets: BudgetDTO[] = [];
  private yearTransactions: TransactionDTO[] = [];

  budgetsRow: {
    id: number;
    amountBudget: string;
    amountTransactions: number;
    categoryId: number;
    categoryName: string
  }[] = [];

  selectedMonth: number = 0;
  selectedYear: number = 0;
  selectedAccount: number | null = null;

  ngOnInit(): void {
    this.selectedMonth = new Date().getMonth();
    this.selectedYear = new Date().getFullYear();
    const accountSelected: AccountDTO | null = this.accountStateService.getSelectedAccount();
    if (accountSelected) {
      this.selectedAccount = accountSelected.id;
    }
    else{
      this.selectedAccount = null;
    }
    this.loadBudgetsAndTransactions(true);
  }

  onSelectYear(year: Date): void {
    this.selectedYear = year.getFullYear();
    this.loadBudgetsAndTransactions(true);
  }

  onSelectMonth(month: Date): void {
    this.selectedMonth = month.getMonth();
    this.loadBudgetsAndTransactions(false);
  }

  onSelectAccount(accountDto: AccountDTO | null): void{
    if(accountDto){
      this.selectedAccount = accountDto.id;
    }
    else{
      this.selectedAccount = null;
    }
    this.loadBudgetsAndTransactions(true);
  }

  private loadBudgetsAndTransactions(changeYearOrAccount: boolean): void {
    if(this.selectedAccount){
      if(changeYearOrAccount){
        this.loadBudgets();
        this.loadTransactions(this.selectedAccount);
      }
      this.budgetsRow = this.yearBudgets
        .filter((budgetDTO: BudgetDTO): boolean => new Date(budgetDTO.startDate).getMonth() == this.selectedMonth)
        .map((budget: BudgetDTO) => ({
            id: budget.id,
            amountBudget: budget.amount,
            amountTransactions: this.yearTransactions
              .filter((transaction: TransactionDTO): boolean => new Date(transaction.date).getMonth() == this.selectedMonth)
              .filter((transaction: TransactionDTO): boolean => transaction.category.id === budget.category.id)
              .reduce((sum: number, transaction: TransactionDTO) => sum + transaction.amount, 0),
            categoryId: budget.category.id,
            categoryName: budget.category.name
        }));
    }
  }

  totalIncome(): number {
    return this.yearTransactions
      .filter((transaction: TransactionDTO): boolean => new Date(transaction.date).getMonth() == this.selectedMonth)
      .filter((transaction: TransactionDTO): boolean => transaction.type === TransactionTypeEnum.INCOME)
      .reduce((sum: number, transaction: TransactionDTO) => sum + transaction.amount, 0)
  }

  totalExpense(): number {
    return this.yearTransactions
      .filter((transaction: TransactionDTO): boolean => new Date(transaction.date).getMonth() == this.selectedMonth)
      .filter((transaction: TransactionDTO): boolean => transaction.type === TransactionTypeEnum.EXPENSE)
      .reduce((sum: number, transaction: TransactionDTO) => sum + transaction.amount, 0)
  }

  totalBalance(): number {
    return this.totalExpense() + this.totalIncome();
  }

  private loadBudgets(): void {
    this.budgetService.findBudgetForYear(this.selectedYear).subscribe({
      next: (response: ApiResponse<BudgetDTO[]>) => {
        this.yearBudgets = response.data;
      }
    });
  }

  private loadTransactions(selectedAccount: number): void {
    this.transactionService.findAllTransactionByAccountId(selectedAccount).subscribe({
      next: (response: ApiResponse<TransactionDTO[]>) => {
        this.yearTransactions = response.data
          .filter((transaction: TransactionDTO): boolean => new Date(transaction.date).getFullYear() == this.selectedYear);
      }
    })
  }

}
