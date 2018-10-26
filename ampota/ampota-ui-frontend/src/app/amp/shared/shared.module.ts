import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddFirebaseTokenInterceptor } from '@app/shared/firebase.request.interceptor';
import { GlobalHttpErrorHandler } from '@app/shared/global.http.error.handler';
import { CardDetailsRenderComponent } from '@app/amp/bundle/card.renderer.component';
import { OwnerRenderComponent } from '@app/amp/bundle/owner-name.renderer.component';
import { PriceRenderComponent } from '@app/amp/bundle/price.renderer.component';
import { BundleDetailsComponent } from '@app/amp/bundle/bundle-details.component';
import { MultipleImageUploaderComponent } from '@app/amp/bundle/multiple-image-uploader.component';
import { CrystalGalleryModule } from 'ngx-crystal-gallery';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CrystalGalleryModule,
    RouterModule
  ],
  declarations: [
    BundleDetailsComponent,
    CardDetailsRenderComponent,
    OwnerRenderComponent,
    PriceRenderComponent,
    MultipleImageUploaderComponent
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
    CardDetailsRenderComponent,
    OwnerRenderComponent,
    PriceRenderComponent,
    MultipleImageUploaderComponent
  ]
})

export class SharedModule { }
