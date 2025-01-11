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

@Component({
  standalone: true,
  selector: 'personal-economy-new-account',
  imports: [ReactiveFormsModule, CommonModule, FormsModule, Card, FloatLabel, InputText, Button],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css'
})
export class NewAccountComponent {

  public accountModel: CreateAccountDTO = {
    bankName: '',
    iban: '',
    alias: ''
  }
  onSubmit(): void  {
    console.log(this.accountModel);
  }
}
