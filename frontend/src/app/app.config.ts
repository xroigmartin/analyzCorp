import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';
import { FilterMatchMode } from 'primeng/api';
import {HttpBackend, provideHttpClient} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {MultiTranslateHttpLoader} from 'ngx-translate-multi-http-loader';

export function multiTranslateLoaderFactory(backend: HttpBackend): TranslateLoader {
  return new MultiTranslateHttpLoader(backend, [
    { prefix: '/assets/i18n/', suffix: '.json' },
    { prefix: '/assets/shared/components/generic-modal/', suffix: '.json' },
    { prefix: '/assets/shared/components/menu/', suffix: '.json' },
    { prefix: '/assets/finance/account/pages/', suffix: '.json' },
  ]);
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(),
    importProvidersFrom([TranslateModule.forRoot({
      defaultLanguage: 'es',
      loader:{
        provide: TranslateLoader,
        useFactory: multiTranslateLoaderFactory,
        deps: [HttpBackend]
      }
    })]),
    providePrimeNG({
      ripple: true,
      filterMatchModeOptions: {
        text: [FilterMatchMode.STARTS_WITH, FilterMatchMode.CONTAINS, FilterMatchMode.NOT_CONTAINS, FilterMatchMode.ENDS_WITH, FilterMatchMode.EQUALS, FilterMatchMode.NOT_EQUALS],
        numeric: [FilterMatchMode.EQUALS, FilterMatchMode.NOT_EQUALS, FilterMatchMode.LESS_THAN, FilterMatchMode.LESS_THAN_OR_EQUAL_TO, FilterMatchMode.GREATER_THAN, FilterMatchMode.GREATER_THAN_OR_EQUAL_TO],
        date: [FilterMatchMode.DATE_IS, FilterMatchMode.DATE_IS_NOT, FilterMatchMode.DATE_BEFORE, FilterMatchMode.DATE_AFTER]
      }
    })
  ]
};
