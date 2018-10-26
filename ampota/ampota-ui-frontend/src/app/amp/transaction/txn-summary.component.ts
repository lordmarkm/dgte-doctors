import { Component, ViewEncapsulation, Input, OnInit, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { Transaction } from '@app/amp/shopping-cart/shopping-cart.model';
import { TransactionService } from './transaction.service';

@Component({
  selector: 'txn-summary',
  templateUrl: './txn-summary.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService, TransactionService ],
  styleUrls: [ './txn-summary.component.scss' ]
})
export class TxnSummaryComponent implements OnInit, OnChanges {

  @Input() txn: Transaction;
  completeTxn: Transaction;

  constructor(private userProfileService: UserProfileService, private txnService: TransactionService, private afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      this.txnService.findOne(this.txn.id).subscribe(t => this.completeTxn = t);
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    const txnChange: SimpleChange = changes.txn;
    let txn = txnChange.currentValue;
    delete this.completeTxn;
    this.txnService.findOne(txn.id).subscribe(t => this.completeTxn = t);
  }

}
