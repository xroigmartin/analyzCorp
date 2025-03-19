import {Component} from '@angular/core';
import {TransactionTypeEnum} from '../../transaction/enums/TransactionTypeEnum.interface';
import {BalanceComponent} from '../balance-component/balance.component';

@Component({
  selector: 'balance-expense',
  imports: [BalanceComponent],
  templateUrl: './expense.component.html',
  standalone: true,
  styleUrl: './expense.component.css'
})
export class ExpenseComponent{
  public transactionType: TransactionTypeEnum = TransactionTypeEnum.EXPENSE;
}
