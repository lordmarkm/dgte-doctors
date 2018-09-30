import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';

import { DialogRef, ModalComponent, CloseGuard } from 'ngx-modialog';
import { BSModalContext } from 'ngx-modialog/plugins/bootstrap';

import { distinctUntilChanged, debounceTime, switchMap, tap, catchError, map } from 'rxjs/operators'
import { Subject, Observable, of, concat } from 'rxjs';
import { Card } from '@app/amp/card/card.model';
import { CardService } from '@app/amp/card/card.service';
import { Bundle } from '@app/amp/bundle/bundle.model';
import { BundleService } from '@app/amp/bundle/bundle.service';
import { Modal, bootstrap4Mode } from 'ngx-modialog/plugins/bootstrap';

export class CustomModalContext extends BSModalContext {
  public bundle: Bundle;
}

/**
 * A Sample of how simple it is to create a new window, with its own injects.
 */
@Component({
  selector: 'modal-content',
  changeDetection: ChangeDetectionStrategy.Default,
  styleUrls: [ './add-bundle.modal.scss' ],
  templateUrl: './add-bundle.modal.html',
  providers: [ CardService, BundleService ]
})
export class AddBundleModalComponent implements CloseGuard, ModalComponent<CustomModalContext>, OnInit {
  context: CustomModalContext;

  //First dropdown
  cardNames$: Observable<string[]>;
  cardNamesLoading: boolean = false;
  cardNamesInput$ = new Subject<string>();
  cardName: string;

  //Second dropdown
  cards$: Observable<Card[]>;
  cardLoading: boolean = false;
  cardsInput$ = new Subject<string>();
  cardImg: string;

  bundle: Bundle = {
    id: 0,
    owner: '',
    card: null,
    qty: 1,
    condition: 'MINT_NEAR_MINT',
    sellMode: 'PIECE_SET',
    sellPrice: 0,
    forSale: true,
  };

  constructor(public dialog: DialogRef<CustomModalContext>, private bundleService: BundleService, private cardService: CardService, private modal: Modal) {
    this.context = dialog.context;
    if (dialog.context.bundle) {
      this.bundle = dialog.context.bundle;
    }
    this.context.dialogClass = 'modal-dialog modal-lg';
    bootstrap4Mode();
    dialog.setCloseGuard(this);
  }

  ngOnInit() {
    this.loadCardNames();
    this.loadCards();
    if (this.bundle.id) {
      console.log('setting existing bundle info into editor');
      this.cardName = this.bundle.card.name;
      this.cardImg = this.bundle.card.imageUris.small;
    }
  }

  beforeClose(): boolean {
    //return true to prevent dialog close
    return false;
  }

  cancel(cardForm) {
    let confirmClose = Promise.resolve(true);
    
    if (cardForm.dirty) {
      confirmClose = this.modal.confirm()
        .title('Cancel')
        .body('Are you sure? You will lose all unsaved data')
        .okBtn('Yes, discard data')
        .cancelBtn('No')
        .open().result;
    }

    confirmClose.then(r => {
      this.dialog.close();
    }, () => { /*do nothing on negative*/ });
  }

  //card selection here
  cardNameSelected(name: string) {
    console.info('card name selected! ' + name);
    delete this.bundle.card;
    this.cardsInput$.next(name);
  }
  cardSelected(card) {
    console.info(JSON.stringify(card));
    this.cardImg = card.imageUris.small;
  }

  private loadCardNames() {
    this.cardNames$ = concat(
      of([]), // default items
      this.cardNamesInput$.pipe(
         debounceTime(200),
         distinctUntilChanged(),
         tap(() => this.cardNamesLoading = true),
         switchMap(term => {
           return this.cardService.getUniqueNames(term, '15').pipe(
             catchError(() => of([])), // empty list on error
             tap(() => this.cardNamesLoading = false));
         })
      )
    );
  }
  private loadCards() {
    let vm = this;
    vm.cards$ = concat(
      of([]), // default items
      vm.cardsInput$.pipe(
         debounceTime(500),
         distinctUntilChanged(),
         tap(() => this.cardLoading = true),
         switchMap(term => {
           return this.cardService.get('name=="' + term + '"', '15').pipe(
             map(page => {
               let cards = page.content
               if (cards.length) {
                 vm.bundle.card = cards[0];
                 vm.cardSelected(cards[0]);
               }
               return cards;
             }),
             catchError(() => of([])), // empty list on error
             tap(() => this.cardLoading = false));
         })
      )
    );
  }


  //Save bundle
  public saveBundle() {
    this.bundleService.save(this.bundle).subscribe(savedBundle => {
      this.dialog.close(savedBundle);
    });
  }

}