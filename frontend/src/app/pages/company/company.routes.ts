import { Routes } from '@angular/router'
import { CompanyComponent } from './pages/company/company.component'

export default [
    {path: '', redirectTo: 'company'},
    {path: 'company', component: CompanyComponent}
] as Routes
