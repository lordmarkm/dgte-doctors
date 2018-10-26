import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { environment } from '../../../environments/environment';
import { AngularFireAuth } from '@angular/fire/auth';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { Bundle } from '@app/amp/bundle/bundle.model';
import { BundleService } from '@app/amp/bundle/bundle.service';
import { CardService } from '@app/amp/card/card.service';
import { distinctUntilChanged, debounceTime, switchMap, tap, catchError, map } from 'rxjs/operators'
import { Subject, Observable, of, concat } from 'rxjs';
import { CardDetailsRenderComponent } from '@app/amp/bundle/card.renderer.component';
import { OwnerRenderComponent } from '@app/amp/bundle/owner-name.renderer.component';
import { PriceRenderComponent } from '@app/amp/bundle/price.renderer.component';
import { IMultiSelectOption, IMultiSelectTexts, IMultiSelectSettings } from 'angular-2-dropdown-multiselect';

//ngx-modialog deps
import { Modal, bootstrap4Mode, BSModalContext } from 'ngx-modialog/plugins/bootstrap';
import { overlayConfigFactory } from "ngx-modialog";

//add-to-cart
import { AddToCartModalComponent } from './add-to-cart.modal.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  providers: [ BundleService, CardService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit{

  //global loading var
  loading: boolean;

  //selected bundle
  bundle: Bundle;

  //filters
  filters:any = {
    cardName: '',
    legality: '',
    colors: []
  }

  //card name filter autocomplete tools
  cardNames$: Observable<string[]>;
  cardNamesLoading: boolean = false;
  cardNamesInput$ = new Subject<string>();

  //card color filter tools
  colorSelectOptions: IMultiSelectOption[] = [
    { id: 'W', name: 'White' },
    { id: 'U', name: 'Blue' },
    { id: 'B', name: 'Black' },
    { id: 'R', name: 'Red' },
    { id: 'G', name: 'Green'}
  ];
  colorSelectSettings: IMultiSelectSettings = {
    displayAllSelectedText: true,
    showUncheckAll: true
  };
  colorSelectTexts: IMultiSelectTexts = {
    uncheckAll: 'Any',
    defaultTitle: 'Select'
  };

  //table
  source: CustomDataSource;
  settings = {
    mode: 'external',
    hideSubHeader: true,
    actions: {
      columnTitle: 'Actions',
      view: false,
      add: false,
      edit: false,
      delete: false,
      custom: [
        { name: 'view-bundle-details', title: '<i class="fa fa-search fa-fw text-success" title="View details"></i>' },
        { name: 'add-to-cart', title: '<i class="fa fa-shopping-cart fa-fw text-primary" title="Add to cart"></i>' }
      ],
      position: 'right'
    },
    columns: {
      cardDetails: {
        title: 'Card',
        type: 'custom',
        renderComponent: CardDetailsRenderComponent
      },
      qty: {
        title: 'Qty',
        type: 'text',
        filter: false,
        width: '65px'
      },
      ownerName: {
        title: 'Owner',
        type: 'custom',
        renderComponent: OwnerRenderComponent
      },
      condition: {
        title: 'Condition',
        filter: false,
        width: '95px',
        type: 'html',
        valuePrepareFunction: value => {
          switch(value) {
          case 'MINT_NEAR_MINT': return '<span class="text-success" title="Mint / Near-mint">M / NM</span>';
          case 'SLIGHTLY_PLAYED': return '<span title="Slightly Played">SP</span>';
          case 'HEAVILY_PLAYED': return '<span class="text-danger" title="Heavily Played">HP</span>';
          default: return value;
          }
        }
      },
      sellPrice: {
        title: 'Price',
        type: 'custom',
        renderComponent: PriceRenderComponent
      },
      createdDate: {
        title: 'Date Added',
        editable: false,
        filter: false,
        valuePrepareFunction: (value) => {return moment(value).format("MMM-DD-YYYY hh:mm a");},
      }
    },
    pager: {
      display: true,
      perPage: 5
    }
  };

  constructor(private http: HttpClient, private cardService: CardService, private bundleService: BundleService, private afAuth: AngularFireAuth,
    private router: Router, private modal: Modal) { }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      console.log(auth);
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/bundle',
        pagerPageKey: 'page',
        sortFieldKey: 'sort',
        pagerLimitKey: 'size',
        dataKey: 'content',
      });
      this.source.setForSale(true);
    });
    this.loadCardNames();
  }

  //card selection here
  refreshFilters() {
    this.source.refreshFilters(this.filters);
  }

  private loadCardNames() {
    this.cardNames$ = concat(
      of([]), // default items
      this.cardNamesInput$.pipe(
         debounceTime(200),
         distinctUntilChanged(),
         tap(() => this.cardNamesLoading = true),
         switchMap(term => {
           if (!term) {
             this.cardNamesLoading = false;
             return of([]);
           }
           return this.cardService.getUniqueNames(term, '15').pipe(
             catchError(() => of([])), // empty list on error
             tap(() => this.cardNamesLoading = false));
         })
      )
    );
  }

  momento(m) {
    return moment(m);
  }

  onCustom(event) {
    switch (event.action) {
      case 'view-bundle-details':
        this.router.navigate(['/amp/market/bundle/' + event.data.id]);
        break;
      case 'add-to-cart':
        this.loading = true;
        const dialogRef = this.modal.open(AddToCartModalComponent, overlayConfigFactory({ bundle: event.data }, BSModalContext));
        //add the dialog's created bundle to the table
        dialogRef.result.then(savedBundle => {
          this.loading = false;
        });
        break;
      default:
        console.error('Unhandled event: ' + event.action);
    }
  }

  onUserRowSelect(event) {
    this.bundle = event.data;
  }

}
