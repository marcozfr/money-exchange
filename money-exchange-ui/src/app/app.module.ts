import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }    from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { QuotationComponent } from './components/quotation/quotation.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AuthInterceptor } from './components/core/auth.interceptor';
import { AuthRedirect } from './components/core/auth.redirect';
import { AuthComponent } from './components/auth/auth.component';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  declarations: [
    AppComponent,
    QuotationComponent,
    NavigationComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TextMaskModule,
    ReactiveFormsModule,
    TextMaskModule
  ],
  providers: [
    AuthRedirect, {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
      deps: []
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
