import { Component, Input, OnInit } from '@angular/core';
import { ViewCell } from 'ng2-smart-table';

@Component({
  template: `
    <div>
        <div *ngIf="sellPrice">{{sellPrice | currency: '₱'}}/<strong class="text-primary">card</strong></div>
        <div *ngIf="sellPriceSet">{{sellPriceSet | currency: '₱'}}/<strong class="text-success">set</strong></div>
        <div *ngIf="sellPriceAll">{{sellPriceAll | currency: '₱'}}/<strong class="text-danger">all</strong></div>
    </div>
  `,
})
export class PriceRenderComponent implements ViewCell, OnInit {

  sellPrice: number;
  sellPriceSet: number;
  sellPriceAll: number;

  @Input() value: string | number | any;
  @Input() rowData: any;

  ngOnInit() {
    let bundle = this.rowData;
    this.sellPrice = bundle.sellPrice;
    this.sellPriceSet = bundle.sellPriceSet;
  }

}
