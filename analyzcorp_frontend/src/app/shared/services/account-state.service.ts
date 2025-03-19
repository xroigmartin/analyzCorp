import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AccountDTO} from '../../personal_economy/bank/account/interfaces/AccountDTO.interfaces';

@Injectable({
  providedIn: 'root'
})
export class AccountStateService {

  private selectedAccountSubject: BehaviorSubject<AccountDTO | null> = new BehaviorSubject<AccountDTO | null>(null);
  private readonly SELECTED_ACCOUNT_KEY: string = "selectedAccount";

  public selectedAccount$: Observable<AccountDTO | null> = this.selectedAccountSubject.asObservable();

  setSelectedAccount(account: AccountDTO | null){
    this.selectedAccountSubject.next(account);

    if(account){
      localStorage.setItem(this.SELECTED_ACCOUNT_KEY, JSON.stringify(account));
    }
    else{
      localStorage.removeItem(this.SELECTED_ACCOUNT_KEY);
    }
  }

  getSelectedAccount(): AccountDTO | null {
    const storedAccount : string | null = localStorage.getItem(this.SELECTED_ACCOUNT_KEY);
    return storedAccount ? JSON.parse(storedAccount) as AccountDTO : null;
  }

  loadSelectedAccountFromStorage() {
    const storedAccount: string | null = localStorage.getItem(this.SELECTED_ACCOUNT_KEY);

    if(storedAccount){
      const parsedAccount: AccountDTO | null = storedAccount ? JSON.parse(storedAccount) as AccountDTO : null;
      this.selectedAccountSubject.next(parsedAccount);
    }
    else{
      this.selectedAccountSubject.next(null);
    }
  }
}
