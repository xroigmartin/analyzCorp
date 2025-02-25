import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {AccountService} from '../../../bank/account/services/account.service';
import {TransactionService} from '../../services/transaction.service';
import {AccountDTO} from '../../../bank/account/interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {Select, SelectChangeEvent} from 'primeng/select';
import {TableModule} from 'primeng/table';
import {TransactionDTO} from '../../interfaces/transactionDTO.interface';
import {CurrencyPipe, DatePipe} from '@angular/common';
import {Button} from 'primeng/button';
import {Dialog} from 'primeng/dialog';
import {RouterLink} from '@angular/router';
import {FileSelectEvent, FileUpload} from 'primeng/fileupload';
import {ALLOWED_FILES_TYPES_IMPORT_TRANSACTIONS} from '../../../../shared/constants/file-types';
import {DropdownModule} from 'primeng/dropdown';
import {FormsModule} from '@angular/forms';
import {TransactionImportFileTypeEnum} from '../../enums/transaction-import-file-type.enum';
import {ToastMessageOptions} from 'primeng/api';
import {MessageComponent} from '../../../../shared/components/message/message.component';

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
    MessageComponent
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
  importDialogVisible: boolean = false;
  selectedFile: File | null = null;
  allowedFileTypes: string = ALLOWED_FILES_TYPES_IMPORT_TRANSACTIONS.join(',');
  accountId: number | null = null;
  transactionFileImportTypes = Object.entries(TransactionImportFileTypeEnum).map(([key, value]) => ({ label: value, value: key }));
  transactionFileImportType: TransactionImportFileTypeEnum | null = null;

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
    this.importDialogVisible = true;
  }

  onFileSelect(event: FileSelectEvent) {
    this.selectedFile = event.files[0] || null;
  }

  uploadFile() {
    if (this.selectedFile && this.accountId && this.transactionFileImportType) {
      console.log(this.selectedFile);
      console.log(this.accountId);
      console.log(this.transactionFileImportType);
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
}
