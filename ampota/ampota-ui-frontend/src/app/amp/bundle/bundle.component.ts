import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import * as moment from 'moment';
import { AngularFireAuth } from '@angular/fire/auth';
import { CustomDataSource } from '@app/shared/smart.table.custom.datasource';
import { Bundle } from './bundle.model';
import { BundleService } from './bundle.service';
import { CardDetailsRenderComponent } from './card.renderer.component';
import { Router } from '@angular/router';

//ngx-modialog deps
import { Modal, bootstrap4Mode, BSModalContext } from 'ngx-modialog/plugins/bootstrap';
import { AddBundleModalComponent } from './add-bundle.modal.component';
import { overlayConfigFactory } from "ngx-modialog";

@Component({
  selector: 'app-bundle',
  templateUrl: './bundle.component.html',
  providers: [ BundleService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: [ './bundle.component.scss' ]
})
export class BundleComponent implements OnInit{
  source: CustomDataSource;
  settings = {
    mode: 'external',
    actions: {
      columnTitle: 'Actions',
      view: true,
      add: true,
      edit: true,
      delete: true,
      custom: [{ name: 'view-bundle-details', title: '<i class="fa fa-search text-success"></i>' }],
      position: 'right' // left|right
    },
    add: {     
      addButtonContent: '<button class="btn btn-primary btn-xs mb-1"><i class="fa fa-plus ml-3 text-success"></i> Add new</button>',
      createButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
      cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
      confirmCreate: true
    },
    edit: {
      editButtonContent: '<i class="fa fa-edit ml-3 text-primary"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="fa fa-close ml-3 text-danger"></i>',
    },
    columns: {
      id: {
        title: 'Id',
        width: '20px',
        filter: false
      },
      cardDetails: {
        title: 'Card',
        type: 'custom',
        renderComponent: CardDetailsRenderComponent
      },
      qty: {
        title: 'Qty',
        type: 'text',
        filter: false,
        width: '20px'
      },
      condition: {
        title: 'Condition',
        filter: false,
        width: '20px',
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
      forSale: {
        title: 'FS',
        type: 'text',
        filter: true,
        width: '20px',
        valuePrepareFunction: value => value ? 'Y' : 'N'
      },
      sellPrice: {
        title: 'Price',
        type: 'text',
        filter: false,
        width: '10px'
      },
      sellPriceSet: {
        title: 'Price/set',
        type: 'text',
        filter: false,
        width: '10px'
      },
      boughtPrice: {
        title: 'Bought',
        type: 'text',
        filter: false,
        width: '10px'
      },
      createdDate: {
        title: 'Date Created',
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

  constructor(public http: HttpClient, public bundleService: BundleService, public afAuth: AngularFireAuth,
    public modal: Modal, private router:Router) { }

  ngOnInit() {
    bootstrap4Mode();
    this.afAuth.authState.subscribe(auth => {
      this.source = new CustomDataSource(this.http, {
        endPoint: environment.ampUrl + '/api/bundle',
        pagerPageKey: 'page',
        sortFieldKey: 'sort',
        pagerLimitKey: 'size',
        dataKey: 'content',
      });
    });
  }

  onCreate(event) {
    const dialogRef = this.modal.open(AddBundleModalComponent, overlayConfigFactory({ }, BSModalContext));
    //add the dialog's created bundle to the table
    dialogRef.result.then(savedBundle => savedBundle && event.source.prepend(savedBundle));
  }

  onCustom(event) {
    let bundle: Bundle = event.data;
    switch (event.action) {
    case 'view-bundle-details':
      this.router.navigate(['/amp/collection/bundle/' + bundle.id]);
      break;
    default:
      console.log('Unrecognized action: ' + event.action);
    }
  }

  onEdit(row) {
    const dialogRef = this.modal.open(AddBundleModalComponent, overlayConfigFactory({ bundle: row.data }, BSModalContext));
    dialogRef.result.then(savedBundle => {
      if (savedBundle) {
        row.setData(savedBundle);
      } else {
        //refresh on cancel to discard changes
        this.source.refresh();
      }
    });
  }

  onDelete(row) {
      this.modal.confirm()
        .title('Delete ' + row.data.card.name)
        .body('Are you sure? This cannot be undone.')
        .okBtn('Yes, delete these cards')
        .cancelBtn('No')
        .open().result.then(confirmed => {
          console.log('delete confirmed? ' + confirmed);
          if (confirmed) {
            this.bundleService.delete(row.data).subscribe(() => {
              this.source.refresh();
            });
          }
        });
  }

  momento(m) {
    return moment(m);
  }
}
