import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {AccountDTO} from '../../interfaces/AccountDTO.interfaces';
import {ApiResponse} from '../../../../shared/interfaces/ApiResponse.interface';
import {ToastMessageOptions} from 'primeng/api';
import {UpdateAccountDTO} from '../../interfaces/UpdateAccountDTO.interface';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {Button} from 'primeng/button';
import {InputText} from 'primeng/inputtext';
import {FloatLabel} from 'primeng/floatlabel';
import {Card} from 'primeng/card';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {BankAccountService} from '../../services/bank-account.service';

@Component({
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    Card,
    FloatLabel,
    InputText,
    Button,
    MessageComponent,
  ],
  selector: 'personal-economy-update-account',
  templateUrl: './update-account.component.html',
  styleUrl: './update-account.component.css',
})
export class UpdateAccountComponent  implements OnInit{

  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute)
  private bankAccountService: BankAccountService = inject(BankAccountService);

  protected successfully: boolean = false;

  public accountModel: UpdateAccountDTO ={
    bankName: '',
    iban: '',
    alias: ''
  }

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  accountId: string | null = null;
  account: AccountDTO | null = null;

  ngOnInit(): void {
    this.accountId = this.route.snapshot.paramMap.get('id');
    if(this.accountId){
      this.bankAccountService.getAccountById(Number(this.accountId)).subscribe({
        next: (apiResponse: ApiResponse<AccountDTO>) => {
          this.account = apiResponse.data;
          this.accountModel ={
            bankName: this.account.bankName,
            iban: this.account.iban,
            alias: this.account.alias,
          }
        }
      });
    }
  }

  onSubmit(): void {
    this.bankAccountService.updateBankAccount(Number(this.accountId), this.accountModel).subscribe({
      next: (response: ApiResponse<AccountDTO>): void =>{
        this.successfully = true;

        const message: ToastMessageOptions = {
          key: 'message',
          sticky: true,
          severity: 'success',
          summary: 'Success',
          detail: `Bank account ${response.data.alias || ''} updated successfully`,
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

  trimIban(): void {
    this.accountModel.iban = this.accountModel.iban.replace(/\s/g, '');
  }

  onCloseMessage(): void{
    if(this.successfully){
      this.successfully = false;
      this.router.navigate(['personal-economy/account']);
    }
  }

}
