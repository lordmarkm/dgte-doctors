import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';

export const routes = [
  { path: '', component: LoginComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AddFirebaseTokenInterceptor,
    multi: true,
  }],
  declarations: [LoginComponent]
})

export class LoginModule { }