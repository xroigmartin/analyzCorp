import {Component, inject, OnInit} from '@angular/core';
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
import {MessageService} from 'primeng/api';

@Component({
  standalone: true,
  selector: 'personal-economy-new-account',
  imports: [ReactiveFormsModule, CommonModule, FormsModule, Card, FloatLabel, InputText, Button, Toast, Avatar],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css',
  providers: [MessageService]
})
export class NewAccountComponent {

  constructor(private messageService: MessageService) {
  }

  public accountModel: CreateAccountDTO = {
    bankName: '',
    iban: '',
    alias: ''
  }

  public visible: boolean = false;

  onSubmit(): void  {
    this.messageService.add({ key: 'confirm', sticky: true, severity: 'success', summary: 'Bank account create successfully' })
    this.visible = true;
    console.log(this.accountModel);
  }

  onConfirm(): void{
    this.messageService.clear('confirm');
    this.visible = false;
  }

  onReject(): void {
    this.messageService.clear('confirm');
    this.visible = false;
  }
}
