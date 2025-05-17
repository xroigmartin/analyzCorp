import { Injectable, signal, computed } from '@angular/core';

export interface layoutConfig {
    preset?: string;
    primary?: string;
    surface?: string | undefined | null;
    darkTheme?: boolean;
    menuMode?: string;
}


@Injectable({
  providedIn: 'root'
})
export class LayoutService {

_config: layoutConfig = {
        preset: 'Aura',
        primary: 'emerald',
        surface: null,
        darkTheme: false,
        menuMode: 'static'
    };

  layoutConfig = signal<layoutConfig>(this._config);
  isDarkTheme = computed(() => this.layoutConfig().darkTheme);

  constructor() { }

  toggleDarkMode(config? : layoutConfig): void {
    const _config = config || this.layoutConfig();
    if(_config.darkTheme){
      document.documentElement.classList.add('app-dark');
    }
    else{
      document.documentElement.classList.remove('app-dark');
    }
  }
}
