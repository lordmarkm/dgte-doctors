import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { AngularFireAuth } from '@angular/fire/auth';
import { Transaction } from '@app/amp/shopping-cart/shopping-cart.model';

@Component({
  selector: 'app-txn-list',
  templateUrl: './txn-list.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ HttpClient ]
})
export class TxnListComponent implements OnInit {
  filters: any = {
    status: '',
    role: ''
  };
  source: CustomDataSource;
  selectedTxn: Transaction;

  settings = {
    actions: {
      columnTitle: 'Actions',
      view: false,
      add: false,
      edit: false,
      delete: false,
      custom: [
        { name: 'view-bundle-details', title: '<i class="fa fa-search fa-fw text-success" title="View details"></i>' },
        { name: 'add-to-cart', title: '<i class="fa fa-shopping-cart fa-fw text-primary" title="Add to cart"></i>' }
      ],
      position: 'right' // left|right
    },
    columns: {
      seller: {
        title: 'Seller',
        width: '20%'
      },
      buyer: {
        title: 'Buyer',
        width: '20%'
      },
      status: {
        title: 'Status'
      },
      deliveryMethod: {
        title: 'Delivery method',
        filter: false
      },
      paymentMethod: {
        title: 'Payment method',
        filter: false
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  constructor(private http: HttpClient, private afAuth: AngularFireAuth) {
  }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/transaction',
        pagerPageKey: 'page',
        sortFieldKey: 'sort',
        pagerLimitKey: 'size',
        dataKey: 'content',
      });
    });
  }

  onUserRowSelect(event) {
    this.selectedTxn = event.data;
  }

}
