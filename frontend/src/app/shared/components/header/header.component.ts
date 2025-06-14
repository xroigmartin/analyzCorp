import { NgClass } from '@angular/common';
import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-shared-header',
  imports: [NgClass],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit{
  @Input() isMenuOpen = true;
  @Output() toggleMenu = new EventEmitter<void>();

  private translate: TranslateService = inject(TranslateService);

  isDarkMode: boolean = false;
  currentLang: string = 'es';

  ngOnInit() {
    this.isDarkMode = document.documentElement.getAttribute('data-theme') === 'dark';
    this.currentLang = this.translate.currentLang || 'es';
  }

  onToggleMenu() {
    this.toggleMenu.emit();
  }

  toggleTheme() {
    this.isDarkMode = !this.isDarkMode;
    const newTheme = this.isDarkMode ? 'dark' : 'light';
    document.documentElement.setAttribute('data-theme', newTheme);
  }

  toggleLanguage() {
    this.currentLang = this.currentLang === 'es' ? 'us' : 'es';
    this.translate.use(this.currentLang);
  }
}
