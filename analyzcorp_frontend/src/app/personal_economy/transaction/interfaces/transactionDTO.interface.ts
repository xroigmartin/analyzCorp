import {TransactionTypeEnum} from '../enums/TransactionTypeEnum.interface';
import {CategoryDTO} from '../../category/interfaces/categoryDTO.interfaces';

export interface TransactionDTO {
  id: number;
  amount: string;
  currency: string;
  date: string;
  type: TransactionTypeEnum;
  category: CategoryDTO;
  description: string;
  accountId: number;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  updatedBy: string;
}
