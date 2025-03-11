import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {AccountService} from '../../../bank/account/services/account.service';
import {TransactionService} from '../../services/transaction.service';
import {AccountDTO} from '../../../bank/account/interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {Select, SelectChangeEvent} from 'primeng/select';
import {TableModule} from 'primeng/table';
import {TransactionDTO} from '../../interfaces/transactionDTO.interface';
import {CurrencyPipe, DatePipe, NgIf} from '@angular/common';
import {Button, ButtonDirective, ButtonIcon} from 'primeng/button';
import {Dialog} from 'primeng/dialog';
import {RouterLink} from '@angular/router';
import {FileSelectEvent, FileUpload} from 'primeng/fileupload';
import {ALLOWED_FILES_TYPES_IMPORT_TRANSACTIONS} from '../../../../shared/constants/file-types';
import {DropdownModule} from 'primeng/dropdown';
import {FormsModule} from '@angular/forms';
import {TransactionImportFileTypeEnum} from '../../enums/transaction-import-file-type.enum';
import {ToastMessageOptions} from 'primeng/api';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {DatePicker} from "primeng/datepicker";
import {Ripple} from 'primeng/ripple';
import {InputText} from 'primeng/inputtext';
import {SelectButton} from 'primeng/selectbutton';
import {InputNumber} from 'primeng/inputnumber';
import {CategoryService} from '../../../category/services/category.service';
import {CategoryDTO} from '../../../category/interfaces/categoryDTO.interfaces';
import {CurrencyDTO} from '../../../../control_panel/currency/interfaces/CurrencyDTO.interfaces';
import {CurrencyService} from '../../../../control_panel/currency/services/currency.service';
import {TransactionTypeEnum} from '../../enums/TransactionTypeEnum.interface';
import {UpdateTransactionDTO} from '../../interfaces/updateTransactionDTO.interface';

@Component({
  selector: 'app-list-transactions',
  imports: [
    Select,
    TableModule,
    DatePipe,
    CurrencyPipe,
    Button,
    RouterLink,
    Dialog,
    FileUpload,
    DropdownModule,
    FormsModule,
    MessageComponent,
    DatePicker,
    ButtonDirective,
    Ripple,
    ButtonIcon,
    NgIf,
    InputText,
    SelectButton
  ],
  templateUrl: './list-transactions.component.html',
  standalone: true,
  styleUrl: './list-transactions.component.css'
})
export class ListTransactionsComponent implements OnInit{

  private readonly accountService: AccountService = inject(AccountService);
  private readonly transactionService: TransactionService = inject(TransactionService);
  private readonly categoriesService: CategoryService = inject(CategoryService);
  private readonly currencyService: CurrencyService = inject(CurrencyService);

  accounts: AccountDTO[] = [];
  accountSelected: AccountDTO | null = null;
  transactions: TransactionDTO[] = [];
  importDialogVisible: boolean = false;
  selectedFile: File | null = null;
  allowedFileTypes: string = ALLOWED_FILES_TYPES_IMPORT_TRANSACTIONS.join(',');
  accountId: number | null = null;
  transactionFileImportTypes = Object.entries(TransactionImportFileTypeEnum).map(([key, value]) => ({ label: value, value: key }));
  transactionFileImportType: TransactionImportFileTypeEnum | null = null;
  categories: CategoryDTO[] = [];
  currencies: CurrencyDTO[] = [];

  public transactionType: any[] = [
    {label: 'INCOME', value:TransactionTypeEnum.INCOME},
    {label: 'EXPENSE', value:TransactionTypeEnum.EXPENSE},
  ];

  public transactionsTable: {id: number;
    amount: string;
    currency: CurrencyDTO;
    date: Date;
    type: TransactionTypeEnum;
    category: CategoryDTO;
    description: string;
    accountId: number;
  }[] = [];

  clonedTransactions: { [s: number]: TransactionDTO } = {};

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  ngOnInit(): void {
    this.accountService.findAllBankAccounts().subscribe({
      next: (apiResponse: ApiResponse<AccountDTO[]>): void => {
        this.accounts = apiResponse.data;
        this.accountSelected = apiResponse.data[0];
        this.loadTransactions(this.accountSelected.id);
      }
    });

    this.categoriesService.findCategories().subscribe({
      next: (apiResponse: ApiResponse<CategoryDTO[]>): void => {
        this.categories = apiResponse.data;
      }
    });

    this.currencyService.findAllCurrencies().subscribe({
      next: (results  : ApiResponse<CurrencyDTO[]>) => {
        this.currencies = results.data;
      }
    });
  }

  loadTransactions(accountId: number): void {
    this.transactionService.findAllTransactionByAccountId(accountId).subscribe({
      next: (apiResponse: ApiResponse<TransactionDTO[]>): void => {
        this.transactions = apiResponse.data;
        this.transactionsTable = apiResponse.data.map((tran: TransactionDTO) =>({
          id: tran.id,
          amount: tran.amount,
          currency: this.getCurrencyDTO(tran.currency),
          date: new Date(tran.date),
          type: tran.type,
          category: tran.category,
          description: tran.description,
          accountId: tran.accountId
        }));
      }
    });
  }

  private getCurrencyDTO(currencyCode: string): CurrencyDTO {
    return this.currencies.find((currency: CurrencyDTO): boolean  => currency.code === currencyCode)!;
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
    this.importDialogVisible = true;
  }

  onFileSelect(event: FileSelectEvent) {
    this.selectedFile = event.files[0] || null;
  }

  uploadFile() {
    if (this.selectedFile && this.accountId && this.transactionFileImportType) {

      this.transactionService.importTransactionsFromFile(this.selectedFile, this.accountId, this.transactionFileImportType).subscribe({
        next: (apiResponse: ApiResponse<string>): void => {
          const message: ToastMessageOptions = {
            key: 'message',
            sticky: true,
            severity: 'success',
            summary: 'Success',
            detail: `Load transactions successfully`,
          };

          this.messageComponent.addMessage(message);
        },
        error: (err) : void => {
          const errorMessage= err.error?.error?.detail || 'Failed to import transactions.';

          const message: ToastMessageOptions = {
            key: 'message',
            sticky: true,
            severity: 'error',
            summary: 'Error',
            detail: errorMessage,
          }
          this.messageComponent.addMessage(message);
        }
      });

      this.importDialogVisible = false;
      this.selectedFile = null;
    }
  }

  onCloseMessage() {
    this.messageComponent.closeMessage();
  }

  onRowEditInit(transaction: TransactionDTO) {
    this.clonedTransactions[transaction.id] = {...transaction};
  }

  onRowEditSave(transactionTable: any) {

    const updateTransactionDTO: UpdateTransactionDTO = {
      accountId: transactionTable.accountId,
      categoryId: transactionTable.category.id,
      currency: transactionTable.currency.code,
      date: transactionTable.date,
      description: transactionTable.description,
      type: transactionTable.type,
      id: transactionTable.id,
      amount: transactionTable.amount

    }

    delete this.clonedTransactions[transactionTable.id];

    this.transactionService.updateTransaction(transactionTable.id, updateTransactionDTO).subscribe({    });
  }

  onRowEditCancel(transaction: TransactionDTO, index: number) {
    this.transactions[index] = this.clonedTransactions[transaction.id];
    delete this.clonedTransactions[transaction.id];
  }

}
