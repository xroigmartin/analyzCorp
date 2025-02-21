import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {Card} from 'primeng/card';
import {InputText} from 'primeng/inputtext';
import {Router} from '@angular/router';
import {TransactionService} from '../../services/transaction.service';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {CreateTransactionDTO} from '../../interfaces/createTransactionDTO.interface';
import {TransactionTypeEnum} from '../../enums/TransactionTypeEnum.interface';
import {FormsModule} from '@angular/forms';
import {InputNumber} from 'primeng/inputnumber';
import {Select} from 'primeng/select';
import {CurrencyService} from '../../../../control_panel/currency/services/currency.service';
import {CurrencyDTO} from '../../../../control_panel/currency/interfaces/CurrencyDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {DatePicker} from 'primeng/datepicker';
import {SelectButton} from 'primeng/selectbutton';
import {AccountService} from '../../../bank/account/services/account.service';
import {AccountDTO} from '../../../bank/account/interfaces/AccountDTO.interfaces';
import {PrimeTemplate} from 'primeng/api';
import {Button} from "primeng/button";

@Component({
  selector: 'app-create-transaction',
  imports: [
    Card,
    InputText,
    FormsModule,
    InputNumber,
    Select,
    DatePicker,
    SelectButton,
    PrimeTemplate,
    Button
  ],
  templateUrl: './create-transaction.component.html',
  standalone: true,
  styleUrl: './create-transaction.component.css'
})
export class CreateTransactionComponent implements OnInit{

  private readonly route: Router = inject(Router);
  private readonly transactionService: TransactionService = inject(TransactionService);
  private readonly currencyService: CurrencyService = inject(CurrencyService);
  private readonly accountService: AccountService = inject(AccountService)

  private successfully: boolean = false;

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  public currencies: CurrencyDTO[] = [];
  public accounts: AccountDTO[] = [];

  public transactionType: any[] = [
    {label: 'INCOME', value:TransactionTypeEnum.INCOME},
    {label: 'EXPENSE', value:TransactionTypeEnum.EXPENSE},
  ];

  public transactionModel = {
    amount: '0.0',
    currency: 'EUR',
    date: new Date().getUTCDate().toString(),
    transactionType: TransactionTypeEnum.INCOME,
    description: '',
    account: null,
  }

  ngOnInit(): void {
    this.currencyService.findAllCurrencies().subscribe({
      next: (results  : ApiResponse<CurrencyDTO[]>) => {
        this.currencies = results.data;
      }
    });

    this.accountService.findAllBankAccounts().subscribe({
      next: (results  : ApiResponse<AccountDTO[]>) => {
        this.accounts = results.data;
      }
    });
  }

  onSubmit(): void {
    console.log(this.transactionModel);

    const createTransactionDTO: CreateTransactionDTO = {
      amount: this.transactionModel.amount,
      currency: this.transactionModel.currency,
      date: this.transactionModel.date,
      transactionType: this.transactionModel.transactionType,
      description: this.transactionModel.description,
      accountId: this.transactionModel.account.id;
    }
  }

  getCurrency() {
    return this.transactionModel.currency;
  }

  cancelForm() {
    this.route.navigate(['personal-economy/transaction']);
  }
}
