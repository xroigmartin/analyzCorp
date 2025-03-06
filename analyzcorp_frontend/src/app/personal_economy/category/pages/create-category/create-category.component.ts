import {Component, inject, ViewChild} from '@angular/core';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {Router} from '@angular/router';
import {CategoryService} from '../../services/category.service';
import {Card} from 'primeng/card';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {CategoryDTO} from '../../interfaces/categoryDTO.interfaces';
import {ToastMessageOptions} from 'primeng/api';

@Component({
  selector: 'app-create-category',
  imports: [
    Card,
    FormsModule,
    InputText,
    Button,
    MessageComponent
  ],
  templateUrl: './create-category.component.html',
  standalone: true,
  styleUrl: './create-category.component.css'
})
export class CreateCategoryComponent {

  private readonly router: Router = inject(Router);
  private readonly categoryService: CategoryService = inject(CategoryService);

  private successfully: boolean = false;

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  public categoryModel: {name: string} = {
    name: ''
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
    this.categoryService.createNewCategory(this.categoryModel.name).subscribe({
      next: (response: ApiResponse<CategoryDTO>) => {
        this.successfully = true;

        const message:  ToastMessageOptions = {
          key: 'message',
          sticky: true,
          severity: 'success',
          summary: 'Success',
          detail: `Category ${response.data.name} created successfully`,
        }

        this.messageComponent.addMessage(message);
      },
      error: (err) : void => {
        const errorMessage= err.error?.error?.detail || 'Failed to create category';

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
