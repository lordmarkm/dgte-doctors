import { Component, ViewEncapsulation, Input, OnInit } from '@angular/core';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { ShoppingCart } from '@app/amp/shopping-cart/shopping-cart.model';
import { ShoppingCartService } from '@app/amp/shopping-cart/shopping-cart.service';
import { Observable } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';
import { Modal, bootstrap4Mode } from 'ngx-modialog/plugins/bootstrap';
import { TransactionService } from '@app/amp/transaction/transaction.service';

@Component({
  selector: 'checkout-component',
  templateUrl: './checkout.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService ],
  styleUrls: [ './checkout.component.scss' ]
})
export class CheckoutComponent implements OnInit{

    currentCart: ShoppingCart;
    cart: Observable<ShoppingCart>;
    txnCount: number;

    constructor(private userProfileService: UserProfileService, private cartService: ShoppingCartService, private afAuth: AngularFireAuth, private modal: Modal,
      private txnService: TransactionService) { }

    ngOnInit() {
        bootstrap4Mode();
        this.cart = this.cartService.cart;
        this.cart.subscribe(c => {
          this.currentCart = c;
          this.txnCount = c.txns.length;
        });
    }

    remove(order) {
      this.cartService.removeOrderFromCart(order);
    }
    removeTxn(txn) {
      this.modal.confirm()
          .title('Remove transaction with ' + txn.sellerName)
          .body('This will remove the entire transaction with ' + txn.sellerName)
          .okBtn('Yes, remove transaction')
          .okBtnClass('btn btn-danger')
          .cancelBtn('No')
          .open().result.then(r => {
              this.cartService.removeTxnFromCart(txn);
          }, () => { /*do nothing on negative*/ });
    }

    verifyAndCheckout() {
      console.log('verifyAndCheckout()');
      this.modal.confirm()
          .title('Verify and Checkout')
          .body('Are you sure?')
          .okBtn('Yes')
          .okBtnClass('btn btn-success')
          .cancelBtn('No')
          .open().result.then(r => {
              this.currentCart.txns.forEach(ctxn => this.txnService.save(ctxn).subscribe(txn => {
                console.log('txn=' + txn);
              }));
          }, () => { /*do nothing on negative*/ });
    }
}
