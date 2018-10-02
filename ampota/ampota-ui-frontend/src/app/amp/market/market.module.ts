import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SearchComponent } from './search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { GlobalHttpErrorHandler } from '@app/shared/global.http.error.handler';
import { AddBundleModalComponent } from '@app/amp/bundle/add-bundle.modal.component';

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
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    RouterModule.forChild(routes),

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
    CrystalGalleryModule
  ],
  declarations: [
    SearchComponent
  ],
  providers:[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AddFirebaseTokenInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: GlobalHttpErrorHandler,
      multi: true,
    },
  ],
  entryComponents: [
  ]
})

export class MarketModule { }
