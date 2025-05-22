import { Component, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from "../../components/menu/menu.component";
import { HeaderComponent } from "../../components/header/header.component";
import { NgIf } from '@angular/common';
import { Sidebar } from "primeng/sidebar";

@Component({
  selector: 'app-shared-layout',
  imports: [RouterOutlet, MenuComponent, HeaderComponent, NgIf, Sidebar],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent implements OnInit {
  showMenu = true;
  isMobile = false;

  ngOnInit(){
    this.checkScreenSize();
  }

  @HostListener('window:resize', [])
  onResize() {
    this.checkScreenSize();
  }

  checkScreenSize() {
    this.isMobile = window.innerWidth < 768;
    if (this.isMobile) {
      this.showMenu = false;
    } else {
      this.showMenu = true;
    }
  }
  
  toggleMenuVisibility() {
    this.showMenu = !this.showMenu;
  }
}
