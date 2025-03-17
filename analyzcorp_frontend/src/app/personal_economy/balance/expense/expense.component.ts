import {Component, inject, OnInit} from '@angular/core';
import {CategoryService} from '../../category/services/category.service';
import {CategoryDTO} from '../../category/interfaces/categoryDTO.interfaces';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {TableModule} from 'primeng/table';
import {DecimalPipe, NgForOf} from '@angular/common';
import {Select, SelectChangeEvent} from 'primeng/select';
import {AccountService} from '../../bank/account/services/account.service';
import {AccountDTO} from '../../bank/account/interfaces/AccountDTO.interfaces';
import {TransactionService} from '../../transaction/services/transaction.service';
import {TransactionDTO} from '../../transaction/interfaces/transactionDTO.interface';

@Component({
  selector: 'app-expense',
  imports: [
    TableModule,
    NgForOf,
    Select,
    DecimalPipe
  ],
  templateUrl: './expense.component.html',
  standalone: true,
  styleUrl: './expense.component.css'
})
export class ExpenseComponent implements OnInit{

  private readonly categoryService: CategoryService = inject(CategoryService);
  private readonly accountService: AccountService = inject(AccountService);
  private readonly transactionService: TransactionService = inject(TransactionService);

  public categories: CategoryDTO[] = [];
  public accounts: AccountDTO[] = [];
  public transactions: TransactionDTO[] = [];
  public expenseMap: Map<string, number[]> = new Map();

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


  selectAccount(event: SelectChangeEvent) {
    this.loadTransactions(event.value.id)
  }

  loadTransactions(accountId: number): void {
    const currentYear: number = new Date().getFullYear();
    this.transactionService.findAllTransactionByAccountId(accountId).subscribe({
      next: (apiResponse: ApiResponse<TransactionDTO[]>) : void => {
        apiResponse.data.forEach((transaction: TransactionDTO) => {
          const categoryName: string = transaction.category.name;
          const amount: number = transaction.amount;
          const month: number = new Date(transaction.date).getMonth();
          const year: number = new Date(transaction.date).getFullYear();

          if(currentYear === year){
            if(!this.expenseMap.has(categoryName)) {
              this.expenseMap.set(categoryName, Array(12).fill(0));
            }

            this.expenseMap.get(categoryName)![month] += amount;
          }
        });

        console.log(this.expenseMap);
      }
    });
  }

  get expenseArray() {
    return Array.from(this.expenseMap.entries());
  }
}
