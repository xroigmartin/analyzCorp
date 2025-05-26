import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-shared-menu',
  imports: [RouterLink, NgFor],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  items = [
      { label: 'New', icon: 'pi pi-plus', route: "/" },
      { label: 'Search', icon: 'pi pi-search', route: "/search" },
    ];
}
