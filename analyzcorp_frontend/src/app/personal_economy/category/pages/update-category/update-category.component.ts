import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../services/category.service';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {CategoryDTO} from '../../interfaces/categoryDTO.interfaces';
import {ToastMessageOptions} from 'primeng/api';
import {Card} from 'primeng/card';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {InputText} from 'primeng/inputtext';

@Component({
  selector: 'app-update-category',
  imports: [
    Card,
    FormsModule,
    Button,
    MessageComponent,
    InputText
  ],
  templateUrl: './update-category.component.html',
  standalone: true,
  styleUrl: './update-category.component.css'
})
export class UpdateCategoryComponent implements OnInit{

  private readonly router: Router = inject(Router);
  private readonly activatedRoute: ActivatedRoute = inject(ActivatedRoute)
  private readonly categoryService: CategoryService = inject(CategoryService);

  private successfully: boolean = false;
  private categoryId: number = 0;

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  public categoryModel: {name: string} = {
    name: ''
  }

  ngOnInit(): void {
    const categoryId: string | null = this.activatedRoute.snapshot.paramMap.get('id');

    if(categoryId) {
      this.categoryId = Number(categoryId);
      this.categoryService.getCategoryById(this.categoryId).subscribe({
        next: (apiResponse: ApiResponse<CategoryDTO>) => {
          this.categoryModel = {
            name: apiResponse.data.name
          }
        }
      });
    }
  }

  cancelForm() {
    this.routeToList();
  }

  onCloseMessage() {
    if(this.successfully){
      this.successfully = false;
      this.routeToList();
    }
  }

  private routeToList(){
    this.router.navigate(['personal-economy/category']);
  }

  onSubmit() {
    this.categoryService.updateCategory(this.categoryModel.name, this.categoryId).subscribe({
      next: (response: ApiResponse<CategoryDTO>) => {
        this.successfully = true;

        const message:  ToastMessageOptions = {
          key: 'message',
          sticky: true,
          severity: 'success',
          summary: 'Success',
          detail: `Category ${response.data.name} updated successfully`,
        }

        this.messageComponent.addMessage(message);
      },
      error: (err) : void => {
        const errorMessage= err.error?.error?.detail || 'Failed to update category';

        const message: ToastMessageOptions = {
          key: 'message',
          sticky: true,
          severity: 'error',
          summary: 'Error',
          detail: errorMessage,
        }
        this.messageComponent.addMessage(message);
      }
    })
  }
}
