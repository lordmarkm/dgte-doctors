import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { ShippingProvider } from './shipping-provider.model';
import { ShippingProviderService } from './shipping-provider.service';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-shipping-provider-list',
  templateUrl: './shipping-provider-list.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ HttpClient, ShippingProviderService ]
})
export class ShippingProviderListComponent implements OnInit {
  source: CustomDataSource;

  settings = {
    actions: {
      columnTitle: 'Actions',
      view: true,
      add: true,
      edit: false,
      delete: false,
      custom: [],
      position: 'right' // left|right
    },
    add: {     
      addButtonContent: '<h4 class="mb-1"><i class="fa fa-plus ml-3 text-success"></i> Add new</h4>',
      createButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
      cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
      confirmCreate: true
    },
    columns: {
      name: {
        title: 'Name',
        width: '20%'
      },
      imageUrl: {
        title: 'Image URL',
        width: '20%',
        type: 'html',
        valuePrepareFunction: (value) => {return '<img src="' + value + '"/>';}
      },
      createdDate: {
        title: 'Date Created',
        editable: false,
        filter: false,
        valuePrepareFunction: (value) => {return moment(value).format("MMM-DD-YYYY hh:mm:ss.SSS a");},
        width: '10%'
      },
      createdBy: {
        title: 'Created by',
        editable: false,
        filter: false,
        width: '10%'
      },
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  constructor(public http: HttpClient, public shippingProviderService: ShippingProviderService,
    public afAuth: AngularFireAuth) {
  }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      console.debug('auth!');
      console.debug(auth);
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/shipping-provider',
        pagerPageKey: 'page',
        sortFieldKey: 'sort',
        pagerLimitKey: 'size',
        dataKey: 'content',
      });
    });
  }

  onUserRowSelect(event) {
    console.info('row select');
    console.info(event);
  }
  onCreateConfirm(event){
    let shippingProvider: ShippingProvider = event.newData;
    this.shippingProviderService.save(shippingProvider).subscribe(savedSp => {
      event.confirm.resolve(savedSp);
    });
  }
}
