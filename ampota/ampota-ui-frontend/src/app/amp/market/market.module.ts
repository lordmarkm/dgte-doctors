import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SearchComponent } from './search.component';
import { BundleDetailsComponent } from '@app/amp/bundle/bundle-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { GlobalHttpErrorHandler } from '@app/shared/global.http.error.handler';
import { AddBundleModalComponent } from '@app/amp/bundle/add-bundle.modal.component';
import { SharedModule } from '@app/amp/shared/shared.module';

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

export const routes = [
  { path: '', component: SearchComponent, pathMatch: 'full', data: { breadcrumb: 'Search' }  },
  { path: 'bundle/:id', component: BundleDetailsComponent, data: { breadcrumb: 'Card Details' } },
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
  ],
  declarations: [
    SearchComponent
  ],
  providers:[],
  entryComponents: []
})

export class MarketModule { }
