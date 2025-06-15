import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Account} from '../model/account';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  private readonly apiUrl = 'http://localhost:8080/api/accounts';

  constructor(private http: HttpClient) {}

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.apiUrl);
  }
}
