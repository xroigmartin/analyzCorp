import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AccountDTO} from '../interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {Observable} from 'rxjs';
import {CreateAccountDTO} from '../interfaces/CreateAccountDTO.interface';
import {UpdateAccountDTO} from '../interfaces/UpdateAccountDTO.interface';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {

  constructor(private httpClient: HttpClient) { }

  findAllBankAccounts(): Observable<ApiResponse<AccountDTO[]>>{
    return this.httpClient.get<ApiResponse<AccountDTO[]>>('http://localhost:8080/api/v1/personal-economy/account', {'headers': this.prepareHeaders()});
  }

  getAccountById(accountId: number): Observable<ApiResponse<AccountDTO>>{
    return this.httpClient.get<ApiResponse<AccountDTO>>(`http://localhost:8080/api/v1/personal-economy/account/${accountId}`,{'headers': this.prepareHeaders()});
  }

  createBankAccount(createBankAccount: CreateAccountDTO): Observable<ApiResponse<AccountDTO>>{
    return this.httpClient.post<ApiResponse<AccountDTO>>("http://localhost:8080/api/v1/personal-economy/account", createBankAccount, {'headers': this.prepareHeaders()});
  }

  updateBankAccount(accountId : number, updateBankAccount: UpdateAccountDTO): Observable<ApiResponse<AccountDTO>>{
    return this.httpClient.put<ApiResponse<AccountDTO>>(`http://localhost:8080/api/v1/personal-economy/account/${accountId}`, updateBankAccount, {'headers': this.prepareHeaders()});
  }

  private prepareHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
}
