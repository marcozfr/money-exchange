import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QuotationComponent } from './components/quotation/quotation.component'
import { AuthComponent } from './components/auth/auth.component'
import { AuthRedirect } from './components/core/auth.redirect'

const routes: Routes = [{
  path : '',
  component : QuotationComponent,
  canActivate : [AuthRedirect]
},{
  path : 'login',
  component : AuthComponent
},{
  path: '**',
  redirectTo: ''
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
