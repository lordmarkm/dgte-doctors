import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { KnowYourCustomerService } from './kyc.service';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';

@Component({
  selector: 'app-kyc',
  templateUrl: './kyc.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ HttpClient, KnowYourCustomerService ],
  styleUrls: ['./kyc.component.scss'],
})
export class KnowYourCustomerComponent {
  private kycService;
  source: CustomDataSource;

  settings = {
    actions: {
      columnTitle: 'Actions',
      view: true,
      add: false,
      edit: false,
      delete: false,
      custom: [],
      position: 'right' // left|right
    },
    columns: {
      mobileNo: {
        title: 'Mobile number',
        width: '10%'
      },
      email: {
        title: 'Email',
        width: '15%'
      },
      firstName: {
        title: 'First name',
        width: '10%'
      },
      lastName: {
        title: 'Last name',
        width: '10%'
      },
      createdDate: {
        title: 'Date Created',
        editable: false,
        filter: false,
        valuePrepareFunction: (value) => {return moment(value).format("MMM-DD-YYYY hh:mm a");},
        width: '15%'
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  constructor(http: HttpClient, kycService: KnowYourCustomerService) {
    this.source = new CustomDataSource(http, {
      endPoint: environment.url + '/api/kyc',
      pagerPageKey: 'page',
      sortFieldKey: 'sort',
      pagerLimitKey: 'size',
      dataKey: 'content',
    });
  }

}
