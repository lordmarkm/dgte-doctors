import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { AngularFireAuth } from '@angular/fire/auth';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { BundleService } from '@app/amp/bundle/bundle.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  providers: [ BundleService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit{

  constructor(public http: HttpClient, public bundleService: BundleService, public afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      console.log(auth);
    });
  }

  momento(m) {
    return moment(m);
  }
}
