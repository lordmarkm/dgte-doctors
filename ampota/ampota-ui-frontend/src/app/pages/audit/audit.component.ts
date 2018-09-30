import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { AuditService } from './audit.service';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ HttpClient, AuditService ],
  styleUrls: ['./audit.component.scss'],
})
export class AuditComponent {
  private auditService;
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
      application: {
        title: 'Application',
        width: '10%'
      },
      method: {
        title: 'Method',
        width: '10%'
      },
      createdDate: {
        title: 'Date Created',
        editable: false,
        filter: false,
        valuePrepareFunction: (value) => {return moment(value).format("MMM-DD-YYYY hh:mm:ss.SSS a");},
        width: '10%'
      },
      txnReference: {
        title: 'Transaction reference',
        width: '10%'
      },
      request: {
        title: 'Request',
        editable: false,
        filter: false,
        width: '30%',
        type: 'html',
        valuePrepareFunction: (value) => {return value ? '<pre class="h100 card bg-faded">' + value + '</pre>' : 'No request';}
      },
      response: {
        title: 'Response',
        editable: false,
        filter: false,
        width: '30%',
        type: 'html',
        valuePrepareFunction: (value) => {return value ? '<pre class="h100 card bg-faded">' + value + '</pre>' : 'No response';}
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  onUserRowSelect(event) {
    console.info(event);
  }

  constructor(http: HttpClient, auditService: AuditService) {
    this.source = new CustomDataSource(http, {
      endPoint: environment.url + '/api/audit',
      pagerPageKey: 'page',
      sortFieldKey: 'sort',
      pagerLimitKey: 'size',
      dataKey: 'content',
    });
  }

}
