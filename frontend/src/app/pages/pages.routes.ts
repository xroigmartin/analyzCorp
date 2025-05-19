import { Routes } from '@angular/router';
import { Empty } from './empty/empty';

export default [
    { path: 'empty', component: Empty },
    { path: '**', redirectTo: '/notfound' }
] as Routes;
