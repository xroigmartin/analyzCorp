import {Component, inject, OnInit} from '@angular/core';
import {TranslatePipe} from '@ngx-translate/core';
import {NgForOf, NgIf} from '@angular/common';
import {Account} from '../../model/account';
import {AccountsService} from '../../services/accounts.service';
import {CardComponent} from '../../../../shared/components/card/card.component';
import {GenericModalComponent} from '../../../../shared/components/generic-modal/generic-modal.component';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {UpdateAccountDTO} from '../../model/updateAccountDTO';
import {ApiResponse} from '../../../../shared/model/apiResponse';

@Component({
  selector: 'app-account',
  imports: [
    TranslatePipe,
    NgForOf,
    CardComponent,
    GenericModalComponent,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit {

  private readonly accountService: AccountsService = inject(AccountsService);
  private fb: FormBuilder = inject(FormBuilder);

  accounts: Account[] = [];
  accountToEdit: Account | null = null;
  showEditModal: boolean = false;
  editForm: FormGroup;
  saving: boolean = false;
  successMessageKey: string | null = null;
  errorMessageKey: string | null = null;

  constructor() {
    this.editForm = this.fb.group({
      name: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.loadAccounts();
  }

  private loadAccounts() {
    this.accountService.getAccounts().subscribe({
      next: (accounts) => {
        this.accounts = accounts;
      },
      error: (error) => {
        console.error('Error loading accounts:', error);
      }
    });
  }

  onEditAccount(accountId: number): void {
    this.accountService.getAccountById(accountId).subscribe({
      next: (account) => {
        this.accountToEdit = {...account};
        this.editForm.patchValue({
          name: account.name
        })
        this.showEditModal = true;
      },
      error: (error) => {
        console.error('Error loading account:', error);
      }
    })

  }

  onSaveAccount(): void {
    if(this.editForm.invalid) {
      this.editForm.markAsTouched();
      return;
    }

    if(this.accountToEdit) {
      this.saving = true;
      const updated: UpdateAccountDTO = {
        name: this.editForm.value.name,
      };

     this.accountService.updateAccount(this.accountToEdit.id, updated).subscribe({
       next: (apiResponse: ApiResponse<Account>):void => {
         this.loadAccounts();
         this.showEditModal = false;
         this.saving = false;
         this.successMessageKey = 'updateSuccess';
       },
       error: () => {
         this.saving = false;
         this.errorMessageKey = 'updateError';
       }
     })
    }
    this.showEditModal = false;
  }

  onDeleteAccount(accountId: number): void {
    console.log("Deleting Account");
  }
}
