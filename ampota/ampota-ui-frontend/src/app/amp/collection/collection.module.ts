import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BinderComponent } from '@app/amp/binder/binder.component';
import { BundleComponent } from '@app/amp/bundle/bundle.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { AddBundleModalComponent } from '@app/amp/bundle/add-bundle.modal.component';
import { BundleDetailsComponent } from '@app/amp/bundle/bundle-details.component';
import { SharedModule } from '@app/amp/shared/shared.module';

//ngx-modialog
import { ModalModule } from 'ngx-modialog';
import { BootstrapModalModule } from 'ngx-modialog/plugins/bootstrap';

//ng-select, the autocomplete for cards search
import { NgSelectModule } from '@ng-select/ng-select';

//ng-number-picker
import {NumberPickerModule} from 'ng-number-picker';

//ngx-toggle-switch
import { UiSwitchModule } from 'ngx-toggle-switch';

export const routes = [
  { path: '', component: BinderComponent, pathMatch: 'full', data: { breadcrumb: 'Binders' }  },
  { path: 'bundle-list', component: BundleComponent, pathMatch: 'full', data: { breadcrumb: 'Cards' } },
  { path: 'bundle/:id', component: BundleDetailsComponent, data: { breadcrumb: 'Card details' } }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    SharedModule,
    RouterModule.forChild(routes),

    //ngx-modialog
    ModalModule.forRoot(),
    BootstrapModalModule,
    //ng-select
    NgSelectModule,
    //ng-number-picker
    NumberPickerModule,
    //ngx-toggle-switch
    UiSwitchModule
  ],
  declarations: [
    BinderComponent,
    BundleComponent,
    AddBundleModalComponent,
  ],
  providers:[],
  entryComponents: [
    AddBundleModalComponent
  ]
})

export class CollectionModule { }
