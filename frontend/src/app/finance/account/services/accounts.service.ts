import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Account} from '../model/account';
import {Observable} from 'rxjs';
import {UpdateAccountDTO} from '../model/updateAccountDTO';
import {ApiResponse} from '../../../shared/model/apiResponse';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  private readonly apiUrl = 'http://localhost:8080/api/accounts';

  constructor(private http: HttpClient) {}

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.apiUrl);
  }

  getAccountById(id: number): Observable<Account> {
    return this.http.get<Account>(`${this.apiUrl}/${id}`);
  }

  updateAccount(accountId: number, updateAccountDTO: UpdateAccountDTO): Observable<ApiResponse<Account>> {
    return this.http.put<ApiResponse<Account>>(`${this.apiUrl}/${accountId}`, updateAccountDTO);
  }
}
