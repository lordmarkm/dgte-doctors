import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { AngularFireAuth } from '@angular/fire/auth';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { Binder } from './binder.model';
import { BinderService } from './binder.service';

@Component({
  selector: 'app-binder',
  templateUrl: './binder.component.html',
  providers: [ BinderService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./binder.component.scss'],
})
export class BinderComponent implements OnInit{
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

  constructor(public http: HttpClient, public binderService: BinderService, public afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      console.log(auth);
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/binder',
        pagerPageKey: 'page',
        sortFieldKey: 'sort',
        pagerLimitKey: 'size',
        dataKey: 'content',
      });
    });
  }

  onCreateConfirm(event){
    let binder: Binder = event.newData;
    this.binderService.save(binder).subscribe(savedBinder => {
      event.confirm.resolve(savedBinder);
    });
  }

  momento(m) {
    return moment(m);
  }
}
