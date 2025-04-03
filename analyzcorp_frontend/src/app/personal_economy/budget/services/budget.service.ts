import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {BudgetDTO} from '../interfaces/budgetDTO.interfaces';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  private httpClient: HttpClient = inject(HttpClient);

  findBudgetForYear(year: number): Observable<ApiResponse<BudgetDTO[]>> {
    return this.httpClient.get<ApiResponse<BudgetDTO[]>>(`http://localhost:8080/api/v1/personal-economy/budgets?year=2025`, {'headers': this.prepareHeaders()})
  }

  private prepareHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
}
