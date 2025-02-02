import {Component, inject, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {CurrencyDTO} from '../../interfaces/CurrencyDTO.interfaces';
import {CurrencyService} from '../../services/currency.service';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';

@Component({
  selector: 'app-list-currencies',
  imports: [
    TableModule
  ],
  templateUrl: './list-currencies.component.html',
  styleUrl: './list-currencies.component.css'
})
export class ListCurrenciesComponent implements OnInit{

  private currencyService: CurrencyService = inject(CurrencyService);

  public currencies: CurrencyDTO[] = [];

  ngOnInit(): void {
    this.currencyService.findAllCurrencies().subscribe({
      next:(apiResponse: ApiResponse<CurrencyDTO[]>): void => {
        this.currencies = apiResponse.data;
      }
    })
  }

}
