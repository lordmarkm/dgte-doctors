import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { AngularFireAuth } from '@angular/fire/auth';
import { Bundle } from './bundle.model';
import { BundleService } from './bundle.service';
import { CardDetailsRenderComponent } from './card.renderer.component';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { map, switchMap } from 'rxjs/operators';

//ngx-modialog deps
import { Modal, bootstrap4Mode, BSModalContext } from 'ngx-modialog/plugins/bootstrap';
import { AddBundleModalComponent } from './add-bundle.modal.component';
import { overlayConfigFactory } from "ngx-modialog";

@Component({
  selector: 'app-bundle',
  templateUrl: './bundle-details.component.html',
  providers: [ BundleService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: [ './bundle-details.component.scss' ]
})
export class BundleDetailsComponent implements OnInit{

  bundle: Bundle;
  editingPictures: boolean = false;
  slideshowPics: any[] = [];
  slideshowConfig = {
    masonry: true,
    masonryMaxHeight: 195,
    masonryGutter: 6,
    loop: false,
    backgroundOpacity: 0.85,
    animationDuration: 100,
    counter: true,
    lightboxMaxHeight: '100vh - 86px',
    lightboxMaxWidth: '100%'
  };

  constructor(private http: HttpClient, private bundleService: BundleService, private afAuth: AngularFireAuth,
    private modal: Modal, private route: ActivatedRoute) { }

  ngOnInit() {
    bootstrap4Mode();
    this.afAuth.authState.subscribe(auth => {
      console.log(auth);
      console.log(this.route.paramMap);
      this.route.params.subscribe(params => { 
        this.bundleService.findOne(params.id).subscribe(bundle => {
          this.bundle = bundle;
          this.initSlideshowPics();
        });
      });
    });
  }

  momento(m) {
    return moment(m);
  }

  picturesUpdated(urls) {
    console.log('Images updated! urls=' + JSON.stringify(urls));
    this.bundle.pictures = urls;
    this.bundleService.save(this.bundle).subscribe(savedBundle => {
      this.bundle = savedBundle;
      this.initSlideshowPics();
    });
    this.editingPictures = false;
  }

  initSlideshowPics() {
    if (this.slideshowPics && this.slideshowPics.length) {
      this.slideshowPics = [];
    }
    if (this.bundle && this.bundle.pictures) {
      this.bundle.pictures.map(pic => {
        this.slideshowPics.push({
          full: pic,
          preview: this.smallImgur(pic),
          class: 'image-thumbnail'
        });
      });
    }
  }

  smallImgur(pic) {
    //reverse the url, replace the first . with '.s' to get thumbnail, then reverse the string again
    let s = pic.split('').reverse().join('').replace('.', '.s').split('').reverse().join('');
    console.info('returning ' + s);
    return s;
  }

}
