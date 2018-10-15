import { Component, Input, OnInit } from '@angular/core';

import { ViewCell } from 'ng2-smart-table';

@Component({
  template: `
    <div>
        <a *ngIf="link" [href]="link" target="_blank" title="Open Facebook">{{name}} <i class="fa fa-facebook"></i></a>
        <span *ngIf="!link">{{name}}</span>
    </div>
  `,
})
export class OwnerRenderComponent implements ViewCell, OnInit {

  link: string;
  name: string;

  @Input() value: string | number | any;
  @Input() rowData: any;

  ngOnInit() {
    let bundle = this.rowData;
    this.name = bundle.ownerName;
    this.link = bundle.ownerLink;
  }

}
