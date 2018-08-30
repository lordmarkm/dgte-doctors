import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProjectService } from './project.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';
import { environment } from '../../../environments/environment'
import * as moment from 'moment';

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
    add: {     
      addButtonContent: '<h4 class="mb-1"><i class="fa fa-plus ml-3 text-success"></i> Add new</h4>',
      createButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
      cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
      confirmCreate: true
    },
    edit: {
      editButtonContent: '<i class="fa fa-pencil mr-3 text-primary"></i>',
      saveButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
      cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
      confirmSave: true
    },
    delete: {
      deleteButtonContent: '<i class="fa fa-trash-o text-danger"></i>',
      confirmDelete: true
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
      },
      createdDate: {
        title: 'Date Created',
        filter: false,
        valuePrepareFunction: (value) => {return moment(value).format("MMM-DD-YYYY");}
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  source: CustomDataSource;

  constructor(http: HttpClient, public projectService: ProjectService) {
    this.source = new CustomDataSource(http, {
      endPoint: environment.url + '/api/project',
      pagerPageKey: 'page',
      sortFieldKey: 'sort',
      pagerLimitKey: 'size',
      dataKey: 'content',
    });
    this.projectService.getProjects();
  }

  public onCreateConfirm(event){
    //console.log(event);
    console.debug('Create confirm!');
    this.projectService.save(event.newData);
    event.confirm.resolve();
  }
  public onSaveConfirm(event) {
    console.debug('Save confirm!');
    event.confirm.resolve();
  }

}

//overriding functions from here https://github.com/akveo/ng2-smart-table/blob/master/src/ng2-smart-table/lib/data-source/server/server.data-source.ts
//TODO put in own file for reusability
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