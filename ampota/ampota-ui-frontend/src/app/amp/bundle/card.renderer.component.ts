import { Component, Input, OnInit } from '@angular/core';

import { ViewCell } from 'ng2-smart-table';

@Component({
  template: `
    <h5>{{name}}</h5>
    <small class="text-muted">{{(set | uppercase) || 'No set'}} | {{lang}}</small>
  `,
})
export class CardDetailsRenderComponent implements ViewCell, OnInit {

  renderValue: string;
  set: string;
  lang: string;
  name: string;

  @Input() value: string | number | any;
  @Input() rowData: any;

  ngOnInit() {
    let card = this.value;
    this.name = card.name;
    this.set = card.setCode;
    this.lang = card.lang;
  }

}
