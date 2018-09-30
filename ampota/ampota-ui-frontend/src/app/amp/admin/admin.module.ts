import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminDashboardComponent } from './admin-dashboard.component';
import { MeetupListComponent } from '@app/amp/meetup/meetup-list.component';
import { ShippingProviderListComponent } from '@app/amp/shipping-provider/shipping-provider-list.component';
import { BankListComponent } from '@app/amp/bank/bank-list.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';

export const routes = [
  { path: '', component: AdminDashboardComponent, pathMatch: 'full' },
  { path: 'meetup-list', component: MeetupListComponent, pathmatch: 'full', data: { breadcrumb: 'Meetup List' } },
  { path: 'shipping-provider-list', component: ShippingProviderListComponent, pathmatch: 'full', data: { breadcrumb: 'Shipping Provider List' } },
  { path: 'bank-list', component: BankListComponent, pathmatch: 'full', data: { breadcrumb: 'Bank List' } },
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    RouterModule.forChild(routes),
  ],
  declarations: [
    AdminDashboardComponent,
    MeetupListComponent,
    ShippingProviderListComponent,
    BankListComponent
  ],
  providers:[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AddFirebaseTokenInterceptor,
      multi: true,
    }
  ]
})

export class AdminModule { }
