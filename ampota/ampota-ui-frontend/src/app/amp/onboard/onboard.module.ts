import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import { TransactionTypesComponent } from './transactiontypes.component';
import { AcceptedCurrencyComponent } from './accepted-currency.component';
import { PrimaryShippingAddressComponent } from './primary-shipping-address.component';
import { ThankYouComponent } from './thank-you.component';
import { ContactComponent } from './contact.component';
import { TagInputModule } from 'ngx-chips';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

export const routes = [
  { path: '', component: WelcomeComponent, pathMatch: 'full' },
  { path: 'contact-info', component: ContactComponent, pathmatch: 'full', data: { breadcrumb: 'Contact Information' } },
  { path: 'transaction-types', component: TransactionTypesComponent, pathMatch: 'full', data: { breadcrumb: 'Transaction Types' } },
  { path: 'accepted-currencies', component: AcceptedCurrencyComponent, pathMatch: 'full', data: { breadcrumb: 'Accepted Currency' } },
  { path: 'shipping-address', component: PrimaryShippingAddressComponent, pathMatch: 'full', data: { breadcrumb: 'Primary Shipping Address' } },
  { path: 'thank-you', component: ThankYouComponent, pathMatch: 'full', data: { breadcrumb: 'Thank You' } }
];

@NgModule({
  imports: [
    CommonModule,
    TagInputModule, 
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
  ],
  declarations: [
    WelcomeComponent,
    ContactComponent,
    TransactionTypesComponent,
    AcceptedCurrencyComponent,
    PrimaryShippingAddressComponent,
    ThankYouComponent
  ]
})

export class OnboardModule { }
