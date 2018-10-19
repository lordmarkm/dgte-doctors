import { NgModule, ErrorHandler } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};
import { routing } from './amp.routing';
import { AmpComponent } from './amp.component';
import { HeaderComponent } from '../theme/components/header/header.component';
import { FooterComponent } from '../theme/components/footer/footer.component';
import { SidebarComponent } from '../theme/components/sidebar/sidebar.component';
import { VerticalMenuComponent } from '../theme/components/menu/vertical-menu/vertical-menu.component';
import { HorizontalMenuComponent } from '../theme/components/menu/horizontal-menu/horizontal-menu.component';
import { BreadcrumbComponent } from '../theme/components/breadcrumb/breadcrumb.component';
import { BackTopComponent } from '../theme/components/back-top/back-top.component';
import { UserMenuComponent } from '../theme/components/user-menu/user-menu.component';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { GlobalHttpErrorHandler } from '@app/shared/global.http.error.handler';
import { UserProfileComponent } from './user-profile/user-profile.component';

//ngx-modialog
import { ModalModule } from 'ngx-modialog';
import { BootstrapModalModule } from 'ngx-modialog/plugins/bootstrap';

//global error handler
import { GlobalErrorHandler } from '@app/shared/global.error.handler';

//ngx-webstorage
import { StorageServiceModule } from 'ngx-webstorage-service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    Ng2SmartTableModule,
    PerfectScrollbarModule,
    routing,

    //ngx-modialog
    ModalModule.forRoot(),
    BootstrapModalModule,

    //ngx-webstorage
    StorageServiceModule,
  ],
  declarations: [
    AmpComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    VerticalMenuComponent,
    HorizontalMenuComponent,
    BreadcrumbComponent,
    BackTopComponent,
    UserMenuComponent,
    UserProfileComponent,
  ],
  providers:[
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    },
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
    //global error handler
    {
      provide: ErrorHandler, 
      useClass: GlobalErrorHandler
    }
  ],
  entryComponents: [
  ]
})
export class AmpModule { }
