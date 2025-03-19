import {Component, inject, OnInit} from '@angular/core';
import {CategoryService} from '../../../../category/services/category.service';
import {AccountService} from '../../../../bank/account/services/account.service';
import {TransactionService} from '../../../../transaction/services/transaction.service';
import {CategoryDTO} from '../../../../category/interfaces/categoryDTO.interfaces';
import {AccountDTO} from '../../../../bank/account/interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../../shared/interfaces/ApiResponse.interface';
import {Select, SelectChangeEvent} from 'primeng/select';
import {TransactionDTO} from '../../../../transaction/interfaces/transactionDTO.interface';
import {FormsModule} from '@angular/forms';
import {DatePicker} from 'primeng/datepicker';
import {TableModule} from 'primeng/table';
import {DecimalPipe, NgForOf} from '@angular/common';
import {TransactionTypeEnum} from '../../../../transaction/enums/TransactionTypeEnum.interface';

@Component({
  selector: 'app-income',
  imports: [
    Select,
    FormsModule,
    DatePicker,
    TableModule,
    DecimalPipe,
    NgForOf
  ],
  templateUrl: './income.component.html',
  standalone: true,
  styleUrl: './income.component.css'
})
export class IncomeComponent implements OnInit{

  private readonly categoryService: CategoryService = inject(CategoryService);
  private readonly accountService: AccountService = inject(AccountService);
  private readonly transactionService: TransactionService = inject(TransactionService);

  public categories: CategoryDTO[] = [];
  public accounts: AccountDTO[] = [];
  public incomeMap: Map<string, number[]> = new Map();
  public yearSelect: Date = new Date();
  public months: string[] = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  private accountSelected: number = 0;

  ngOnInit(): void {
    this.categoryService.findCategories().subscribe({
      next:((apiResponse: ApiResponse<CategoryDTO[]>) => {
        this.categories = apiResponse.data;
      })
    });

    this.accountService.findAllBankAccounts().subscribe({
      next:((apiResponse: ApiResponse<AccountDTO[]>) => {
        this.accounts = apiResponse.data;
      })
    })
  }


  selectAccount(event: SelectChangeEvent): void {
    this.accountSelected = event.value.id;
    this.loadTransactions(this.accountSelected)
  }

  loadTransactions(accountId: number): void {
    this.transactionService.findAllTransactionByAccountId(accountId).subscribe({
      next: (apiResponse: ApiResponse<TransactionDTO[]>) : void => {

        this.incomeMap.clear();

        apiResponse.data
          .filter((transaction: TransactionDTO) : boolean => transaction.type === TransactionTypeEnum.INCOME)
          .forEach((transaction: TransactionDTO): void => {

          const categoryName: string = transaction.category.name;
          const amount: number = transaction.amount;
          const month: number = new Date(transaction.date).getMonth();
          const year: number = new Date(transaction.date).getFullYear();

          if(this.yearSelect.getFullYear() === year){
            if(!this.incomeMap.has(categoryName)) {
              this.incomeMap.set(categoryName, Array(12).fill(0));
            }
            this.incomeMap.get(categoryName)![month] += amount;
          }

        });
      }
    });
  }

  get incomeArray() {
    return Array.from(this.incomeMap.entries()).map(([category, amounts]) => ({
      category,
      amounts
    }));
  }

  updateTransaction(event: Date) {
    this.yearSelect = event;
    this.loadTransactions(this.accountSelected);
  }
}
