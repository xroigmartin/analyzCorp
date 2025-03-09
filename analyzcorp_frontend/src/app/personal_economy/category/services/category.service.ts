import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../../../shared/interfaces/ApiResponse.interface';
import {CategoryDTO} from '../interfaces/categoryDTO.interfaces';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  findCategories(): Observable<ApiResponse<CategoryDTO[]>>{
    return this.httpClient.get<ApiResponse<CategoryDTO[]>>('http://localhost:8080/api/v1/personal-economy/categories', {'headers': this.prepareHeaders()});
  }

  createNewCategory(name: string): Observable<ApiResponse<CategoryDTO>>{
    return this.httpClient.post<ApiResponse<CategoryDTO>>('http://localhost:8080/api/v1/personal-economy/categories', name, {'headers': this.prepareHeaders()});
  }

  getCategoryById(categoryId: number): Observable<ApiResponse<CategoryDTO>>{
    return this.httpClient.get<ApiResponse<CategoryDTO>>(`http://localhost:8080/api/v1/personal-economy/categories/${categoryId}`, {'headers': this.prepareHeaders()});
  }

  updateCategory(name: string, categoryId: number): Observable<ApiResponse<CategoryDTO>>{
    return this.httpClient.put<ApiResponse<CategoryDTO>>(`http://localhost:8080/api/v1/personal-economy/categories/${categoryId}`, name, {'headers': this.prepareHeaders()});
  }

  private prepareHeaders(): HttpHeaders{
    return new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
}
