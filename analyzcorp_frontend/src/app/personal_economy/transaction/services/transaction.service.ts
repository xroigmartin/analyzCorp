import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {TransactionDTO} from '../interfaces/transactionDTO.interface';
import {CreateTransactionDTO} from '../interfaces/createTransactionDTO.interface';
import {AccountDTO} from '../../bank/account/interfaces/AccountDTO.interfaces';
import {UpdateTransactionDTO} from '../interfaces/updateTransactionDTO.interface';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private httpClient: HttpClient) { }

  findAllTransactionByAccountId(accountId: number): Observable<ApiResponse<TransactionDTO[]>>{
    return this.httpClient.get<ApiResponse<TransactionDTO[]>>(`http://localhost:8080/api/v1/personal-economy/transactions?accountId=${accountId}`, {'headers': this.prepareHeaders()});
  }

  createTransaction(createTransaction: CreateTransactionDTO): Observable<ApiResponse<TransactionDTO>>{
    return this.httpClient.post<ApiResponse<TransactionDTO>>("http://localhost:8080/api/v1/personal-economy/transactions", createTransaction, {'headers': this.prepareHeaders()})
  }

  importTransactionsFromFile(file: File, accountId: number, fileImportType: string): Observable<ApiResponse<string>>{
    const formData: FormData = new FormData();
    formData.append('accountId', accountId.toString());
    formData.append('fileImportType', fileImportType);
    formData.append('file', file);

    return this.httpClient.post<ApiResponse<string>>("http://localhost:8080/api/v1/personal-economy/transactions/import_file", formData);
  }

  updateTransaction(transactionId: number, updateTransaction: UpdateTransactionDTO) {
    return this.httpClient.put<ApiResponse<TransactionDTO>>(`http://localhost:8080/api/v1/personal-economy/transactions/${transactionId}`, updateTransaction, {'headers': this.prepareHeaders()})
  }

  private prepareHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }


}
