import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { ProjectService } from './project.service';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  encapsulation: ViewEncapsulation.None,
  providers:[ ]
})
export class ProjectComponent {
  private projectService;

  settings = {
    actions: {
      columnTitle: 'Actions',
      view: true,
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
        editable: false,
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

  constructor(http: HttpClient, projectService: ProjectService) {
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
    this.projectService.save(event.newData);
    event.confirm.resolve(event.newData);
  }

}
