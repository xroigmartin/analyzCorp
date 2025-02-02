import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {CurrencyDTO} from '../interfaces/CurrencyDTO.interfaces';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private httpClient: HttpClient) { }

  findAllCurrencies():Observable<ApiResponse<CurrencyDTO[]>>{
    return this.httpClient.get<ApiResponse<CurrencyDTO[]>>("http://localhost:8080/api/v1/control-panel/currencies", {"headers": this.prepareHeaders()});
  }

  private prepareHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
}
