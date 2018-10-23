import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { ShoppingCart } from '@app/amp/shopping-cart/shopping-cart.model';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { Order } from './shopping-cart.model';
import { ShoppingCartService } from './shopping-cart.service';
import { Observable } from 'rxjs';
import { Modal, bootstrap4Mode } from 'ngx-modialog/plugins/bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'shopping-cart',
  templateUrl: './shopping-cart.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService ],
  styleUrls: [ './shopping-cart.component.scss' ]
})
export class ShoppingCartComponent implements OnInit {

  owner: UserProfile;  cart: Observable<ShoppingCart>;

  constructor(private modal: Modal, private userProfileService: UserProfileService,private cartService: ShoppingCartService, private afAuth: AngularFireAuth,
          private router:Router) { }

  ngOnInit() {
      bootstrap4Mode();
      this.afAuth.authState.subscribe(auth => auth);
    //this.cartService.cart.subscribe(cart => {
    //  console.log('cart change: ' + JSON.stringify(cart));
    //  this.cart = cart
    //});
      this.cart = this.cartService.cart;
  }

  removeOrder(order) {
      this.cartService.removeOrderFromCart(order);
  }
  removeTxn(txn) {
      this.modal.confirm()
          .title('Remove transaction with ' + txn.sellerName)
          .body('This will remove the entire transaction with ' + txn.sellerName + ': <br>' + this.modalOrderList(txn.orders))
          .okBtn('Yes, remove transaction')
          .okBtnClass('btn btn-danger')
          .cancelBtn('No')
          .open().result.then(r => {
              this.cartService.removeTxnFromCart(txn);
          }, () => { /*do nothing on negative*/ });
  }
  modalOrderList(orders: Order[]) {
      return orders.map(order => order.qty + 'x ' + order.bundle.card.name)
          .join('<br>');
  }

  checkout() {
      this.router.navigate(['/amp/market/checkout']);
  }
}
