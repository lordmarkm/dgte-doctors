import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProjectService } from './project.service';
import { HttpClient } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment'

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ ProjectService ]
})
export class ProjectComponent {

  settings = {
    actions: {
      columnTitle: 'Actions',
      add: true,
      edit: true,
      delete: true,
      custom: [],
      position: 'right' // left|right
    },
    columns: {
      id: {
        title: 'ID',
        editable: false,
        filter: false,
        width: '60px',
        type: 'html',
        valuePrepareFunction: (value) => { return '<div class="text-center">' + value + '</div>'; }
      },
      name: {
        title: 'Name',
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  source: CustomDataSource;

  constructor(http: HttpClient) {
    this.source = new CustomDataSource(http, {
      endPoint: environment.url + '/api/project',
      pagerPageKey: 'page',
      sortFieldKey: 'sort',
      pagerLimitKey: 'size',
      dataKey: 'content',
    });
  }

}

//overriding functions from here https://github.com/akveo/ng2-smart-table/blob/master/src/ng2-smart-table/lib/data-source/server/server.data-source.ts
export class CustomDataSource extends ServerDataSource {

  protected addSortRequestParams(httpParams: HttpParams): HttpParams {
    if (this.sortConf) {
      this.sortConf.forEach((fieldConf) => {
        let sortKey = fieldConf.field + ',' + fieldConf.direction.toUpperCase();
        httpParams = httpParams.set('sort', sortKey);
      });
    }
    return httpParams;
  }

  protected addFilterRequestParams(httpParams: HttpParams): HttpParams {
    let term = '';
    if (this.filterConf.filters) {
      this.filterConf.filters.forEach((fieldConf: any) => {
        if (fieldConf['search']) {
          if (term.length) {
            term += ';';
          }
          term += fieldConf['field'] + '==' + fieldConf['search'];
        }
      });
    }
    httpParams = httpParams.set('term', term);
    return httpParams;
  }

  protected extractTotalFromResponse(res: any): number {
    return res.body.totalElements;
  }

}