import {TransactionTypeEnum} from '../../transaction/enums/TransactionTypeEnum.interface';

export interface CategoryDTO {
  id: number;
  name: string;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  updatedBy: string;
}
