import {TransactionTypeEnum} from '../enums/TransactionTypeEnum.interface';

export interface CreateTransactionDTO {
  amount: string;
  currency: string;
  date: string;
  categoryId: number | null;
  type: TransactionTypeEnum;
  description: string;
  accountId: number | null;
}
