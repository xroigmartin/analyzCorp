import {Component} from '@angular/core';
import {TransactionTypeEnum} from '../../../../transaction/enums/TransactionTypeEnum.interface';
import {BalanceComponent} from '../../../balance-component/balance.component';

@Component({
  selector: 'balance-income',
  imports: [BalanceComponent],
  templateUrl: './income.component.html',
  standalone: true,
  styleUrl: './income.component.css'
})
export class IncomeComponent{
  public transactionType: TransactionTypeEnum = TransactionTypeEnum.INCOME;
}
