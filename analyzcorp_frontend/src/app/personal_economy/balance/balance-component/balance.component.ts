import {Component, inject, Input, OnInit} from '@angular/core';
import {CategoryService} from '../../category/services/category.service';
import {TransactionService} from '../../transaction/services/transaction.service';
import {AccountStateService} from '../../../shared/services/account-state.service';
import {CategoryDTO} from '../../category/interfaces/categoryDTO.interfaces';
import {AccountDTO} from '../../bank/account/interfaces/AccountDTO.interfaces';
import {TransactionTypeEnum} from '../../transaction/enums/TransactionTypeEnum.interface';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {TransactionDTO} from '../../transaction/interfaces/transactionDTO.interface';
import {AccountSelectorComponent} from '../../../shared/components/select/account-selector/account-selector.component';
import {
  YearDatePickerComponent
} from '../../../shared/components/date_picker/year-date-picker/year-date-picker.component';
import {TableModule} from 'primeng/table';
import {DecimalPipe, NgForOf} from '@angular/common';

@Component({
  selector: 'balance-component',
  imports: [
    AccountSelectorComponent,
    YearDatePickerComponent,
    TableModule,
    NgForOf,
    DecimalPipe
  ],
  templateUrl: './balance.component.html',
  standalone: true,
  styleUrl: './balance.component.css'
})
export class BalanceComponent implements OnInit{

  @Input() transactionType: TransactionTypeEnum = TransactionTypeEnum.EXPENSE;
  @Input() months: string[] = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  public categories: CategoryDTO[] = [];
  public transactionMap: Map<string, number[]> = new Map();
  public yearSelect: Date = new Date();

  private accountSelected: AccountDTO | null = null;

  private readonly categoryService: CategoryService = inject(CategoryService);
  private readonly transactionService: TransactionService = inject(TransactionService);
  private readonly accountStateService: AccountStateService = inject(AccountStateService);

  ngOnInit(): void {
    this.accountSelected = this.accountStateService.getSelectedAccount();
    this.categoryService.findCategories().subscribe({
      next: (apiResponse: ApiResponse<CategoryDTO[]>) => {
        this.categories = apiResponse.data;
      }
    });

    this.loadTransactions();
  }

  selectAccount(account: AccountDTO | null): void {
    this.accountSelected = account;
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionMap.clear();
    if (this.accountSelected) {
      this.transactionService.findAllTransactionByAccountId(this.accountSelected.id).subscribe({
        next: (apiResponse: ApiResponse<TransactionDTO[]>) => {
          apiResponse.data
            .filter((transaction: TransactionDTO) => transaction.type === this.transactionType)
            .forEach((transaction: TransactionDTO) => {
              const categoryName: string = transaction.category.name;
              const amount: number = transaction.amount;
              const month: number = new Date(transaction.date).getMonth();
              const year: number = new Date(transaction.date).getFullYear();

              if (this.yearSelect.getFullYear() === year) {
                if (!this.transactionMap.has(categoryName)) {
                  this.transactionMap.set(categoryName, Array(12).fill(0));
                }
                this.transactionMap.get(categoryName)![month] += amount;
              }
            });
        }
      });
    }
  }

  get transactionArray() {
    return Array.from(this.transactionMap.entries()).map(([category, amounts]) => ({
      category,
      amounts
    }));
  }

  onSelectYear(year: Date) {
    this.yearSelect = year;
    this.loadTransactions();
  }

}
