import { Component } from '@angular/core';
import {TableModule} from 'primeng/table';
import {AccountDTO} from '../../interfaces/AccountDTO.interfaces';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';

@Component({
  standalone: true,
  selector: 'personal-economy-list-accounts',
  imports: [TableModule, Button, RouterLink],
  templateUrl: './list-accounts.component.html',
  styleUrl: './list-accounts.component.css'
})
export class ListAccountsComponent {

  accounts: AccountDTO[] = [];
}
