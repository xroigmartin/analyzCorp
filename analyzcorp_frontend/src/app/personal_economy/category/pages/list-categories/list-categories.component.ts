import {Component, inject, OnInit} from '@angular/core';
import {CategoryService} from '../../services/category.service';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {CategoryDTO} from '../../interfaces/categoryDTO.interfaces';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';
import {TableModule} from 'primeng/table';

@Component({
  selector: 'app-list-categories',
  imports: [
    Button,
    RouterLink,
    TableModule
  ],
  templateUrl: './list-categories.component.html',
  standalone: true,
  styleUrl: './list-categories.component.css'
})
export class ListCategoriesComponent implements OnInit{

  private readonly categoryService = inject(CategoryService);

  categories: CategoryDTO[] = [];

  ngOnInit() {
    this.categoryService.findCategories().subscribe({
      next: (response: ApiResponse<CategoryDTO[]>) => {
        this.categories = response.data;
      }
    });
  }
}
