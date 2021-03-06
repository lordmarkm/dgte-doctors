import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SearchComponent } from './search.component';
import { BundleDetailsComponent } from './bundle-details.component';
import { BundleOwnerDetailsComponent } from './bundle-owner-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { GlobalHttpErrorHandler } from '@app/shared/global.http.error.handler';
import { AddToCartModalComponent } from './add-to-cart.modal.component';
import { SharedModule } from '@app/amp/shared/shared.module';
import { ShoppingCartComponent } from '@app/amp/shopping-cart/shopping-cart.component';
import { CheckoutComponent } from '@app/amp/market/checkout.component';
import { TransactionService } from '@app/amp/transaction/transaction.service';
import { TxnListComponent } from '@app/amp/transaction/txn-list.component';
import { TxnSummaryComponent } from '@app/amp/transaction/txn-summary.component';

//multiselect dropdown
import { MultiselectDropdownModule } from 'angular-2-dropdown-multiselect';
//ngx-modialog
import { ModalModule } from 'ngx-modialog';
import { BootstrapModalModule } from 'ngx-modialog/plugins/bootstrap';
//ng-select, the autocomplete for cards search
import { NgSelectModule } from '@ng-select/ng-select';
//ng-number-picker
import {NumberPickerModule} from 'ng-number-picker';
//ngx-toggle-switch
import { UiSwitchModule } from 'ngx-toggle-switch';
//ngx-crystal-gallery
import { CrystalGalleryModule } from 'ngx-crystal-gallery';
//ngx-store
import { WebStorageModule } from 'ngx-store';

export const routes = [
  { path: '', component: SearchComponent, pathMatch: 'full', data: { breadcrumb: 'Search' }  },
  { path: 'bundle/:id', component: BundleDetailsComponent, data: { breadcrumb: 'Card Details' } },
  { path: 'checkout', component: CheckoutComponent, data: { breadcrumb: 'Checkout' } },
  { path: 'txns', component: TxnListComponent, data: { breadcrumb: 'Transactions' } },
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    RouterModule.forChild(routes),
    SharedModule,

    //ngx-modialog
    ModalModule.forRoot(),
    BootstrapModalModule,
    //ng-select
    NgSelectModule,
    //ng-number-picker
    NumberPickerModule,
    //ngx-toggle-switch
    UiSwitchModule,
    //ngx-crystal-gallery
    CrystalGalleryModule,
    //multiselect dropdown
    MultiselectDropdownModule,
    //ngx-store
    WebStorageModule,
  ],
  declarations: [
    SearchComponent,
    BundleDetailsComponent,
    BundleOwnerDetailsComponent,
    ShoppingCartComponent,
    AddToCartModalComponent,
    CheckoutComponent,
    TxnListComponent,
    TxnSummaryComponent
  ],
  providers:[
    TransactionService,
  ],
  entryComponents: [
    AddToCartModalComponent,
    TxnSummaryComponent
  ]
})

export class MarketModule { }
