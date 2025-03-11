import {TransactionTypeEnum} from '../enums/TransactionTypeEnum.interface';

export interface UpdateTransactionDTO {
  id: number;
  amount: string;
  currency: string;
  date: string;
  categoryId: number | null;
  type: TransactionTypeEnum;
  description: string;
  accountId: number | null;
}
