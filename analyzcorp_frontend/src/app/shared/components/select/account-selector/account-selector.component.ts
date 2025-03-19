import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {AccountService} from '../../../../personal_economy/bank/account/services/account.service';
import {AccountDTO} from '../../../../personal_economy/bank/account/interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../interfaces/ApiResponse.interface';
import {Select} from 'primeng/select';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'account-selector',
  imports: [
    Select,
    FormsModule
  ],
  templateUrl: './account-selector.component.html',
  standalone: true,
  styleUrl: './account-selector.component.css'
})
export class AccountSelectorComponent implements OnInit{

  private readonly accountService: AccountService = inject(AccountService);

  public accounts: AccountDTO[] = [];
  public accountSelected: AccountDTO | null = null;
  @Output() accountValueChange: EventEmitter<AccountDTO | null> = new EventEmitter<AccountDTO | null>();

  ngOnInit(): void {
    this.accountService.findAllBankAccounts().subscribe({
      next: (apiResponse: ApiResponse<AccountDTO[]>): void => {
        this.accounts = apiResponse.data;
      }
    });
  }

  onSelectChange(): void{
    this.accountValueChange.emit(this.accountSelected);
  }

}
