import { Component, Input, OnInit } from '@angular/core';

import { ViewCell } from 'ng2-smart-table';

@Component({
  template: `
    <div><strong>{{name}}</strong></div>
    <img [src]="imgUrl" />
    <div><small class="text-muted">{{(set | uppercase) || 'No set'}} | {{lang}}</small></div>
  `,
  styles: ['img { width: 75%; height: 750%; }']
})
export class CardDetailsRenderComponent implements ViewCell, OnInit {

  renderValue: string;
  set: string;
  lang: string;
  name: string;
  imgUrl: string;

  @Input() value: string | number | any;
  @Input() rowData: any;

  ngOnInit() {
    let card = this.value;
    this.name = card.name;
    this.set = card.setCode;
    this.lang = card.lang;
    this.imgUrl = card.imageUris.small;
  }

}
