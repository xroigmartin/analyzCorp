import {TransactionTypeEnum} from '../enums/TransactionTypeEnum.interface';

export interface TransactionDTO {
  id: number;
  amount: string;
  currency: string;
  date: string;
  type: TransactionTypeEnum;
  description: string;
  accountId: number;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  updatedBy: string;
}
