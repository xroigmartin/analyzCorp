import {CategoryDTO} from '../../category/interfaces/categoryDTO.interfaces';

export interface BudgetDTO {
  id: number;
  category: CategoryDTO;
  amount: string;
  startDate: string;
  endDate: string;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  updatedBy: string;
}
