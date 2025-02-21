import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {CreateAccountDTO} from '../../interfaces/CreateAccountDTO.interface';
import {Card} from 'primeng/card';
import {FloatLabel} from 'primeng/floatlabel';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {MessageService, ToastMessageOptions} from 'primeng/api';
import {MessageComponent} from '../../../../../shared/components/message/message.component';
import {Router} from '@angular/router';
import {AccountService} from '../../services/account.service';
import {ApiResponse} from '../../../../../shared/interfaces/ApiResponse.interface';
import {AccountDTO} from '../../interfaces/AccountDTO.interfaces';

@Component({
  standalone: true,
  selector: 'personal-economy-new-account',
  imports: [ReactiveFormsModule, CommonModule, FormsModule, Card, FloatLabel, InputText, Button, MessageComponent],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css',
})
export class NewAccountComponent {

  private router: Router = inject(Router);
  private bankAccountService: AccountService = inject(AccountService);

  private successfully: boolean = false;

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  public accountModel: CreateAccountDTO = {
    bankName: '',
    iban: '',
    alias: ''
  }

  onSubmit(): void  {

    this.bankAccountService.createBankAccount(this.accountModel).subscribe({
      next: (response: ApiResponse<AccountDTO>): void =>{
        this.successfully = true;

        let message: ToastMessageOptions = {};

        if(response.data.alias){
          message = {
            key: 'message',
            sticky: true,
            severity: 'success',
            summary: 'Success',
            detail: `Bank account ${response.data.alias} created successfully`,
          }
        }
        else{
          message = {
            key: 'message',
            sticky: true,
            severity: 'success',
            summary: 'Success',
            detail: 'Bank account created successfully',
          }
        }
        this.messageComponent.addMessage(message);
      },
      error: (err) : void => {
        const errorMessage= err.error?.error?.detail || 'Failed to create bank account';

        const message: ToastMessageOptions = {
          key: 'message',
          sticky: true,
          severity: 'error',
          summary: 'Error',
          detail: errorMessage,
        }
        this.messageComponent.addMessage(message);
      }
    });
   }

  onCloseMessage(): void{
    if(this.successfully){
      this.successfully = false;
      this.router.navigate(['personal-economy/account']);
    }
  }

  trimIban() : void {
    this.accountModel.iban = this.accountModel.iban.replace(/\s/g, "");
  }

  cancelForm(): void{
    this.router.navigate(['personal-economy/account']);
  }
}
