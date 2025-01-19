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
import {Toast} from 'primeng/toast';
import {Avatar} from 'primeng/avatar';
import {Message} from 'primeng/message';
import {MessageService, ToastMessageOptions} from 'primeng/api';
import {MessageComponent} from '../../../../shared/components/message/message.component';
import {Router} from '@angular/router';

@Component({
  standalone: true,
  selector: 'personal-economy-new-account',
  imports: [ReactiveFormsModule, CommonModule, FormsModule, Card, FloatLabel, InputText, Button, MessageComponent],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css',
})
export class NewAccountComponent {

  private router: Router = inject(Router);

  private successfuly: boolean = false;

  @ViewChild('message')
  public messageComponent!: MessageComponent;

  public accountModel: CreateAccountDTO = {
    bankName: '',
    iban: '',
    alias: ''
  }

  onSubmit(): void  {

    this.successfuly = true;

    const message: ToastMessageOptions = {
      key:'confirm',
      sticky: true,
      summary:'Bank account created successfully.',
      severity: 'success',
    };

    this.messageComponent.addMessage(message)

    console.log(this.accountModel);
  }

  onCloseMessage(): void{
    if(this.successfuly){
      this.successfuly = false;
      this.router.navigate(['personal-economy/account'])
    }
  }
}
