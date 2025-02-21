import {TransactionTypeEnum} from '../enums/TransactionTypeEnum.interface';

export interface CreateTransactionDTO {
  amount: string;
  currency: string;
  date: string;
  transactionType: TransactionTypeEnum;
  description: string;
  account: number;
}
