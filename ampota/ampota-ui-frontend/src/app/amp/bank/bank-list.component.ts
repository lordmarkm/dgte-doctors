import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { Bank } from './bank.model';
import { BankService } from './bank.service';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-bank-list',
  templateUrl: './bank-list.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ HttpClient, BankService ]
})
export class BankListComponent implements OnInit {
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

  constructor(public http: HttpClient, public bankService: BankService,
    public afAuth: AngularFireAuth) {
  }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/bank',
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
    let bank: Bank = event.newData;
    this.bankService.save(bank).subscribe(savedBank => {
      event.confirm.resolve(savedBank);
    });
  }
}
