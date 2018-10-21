import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { ShoppingCart } from '@app/amp/shopping-cart/shopping-cart.model';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { ShoppingCartService } from './shopping-cart.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'shopping-cart',
  templateUrl: './shopping-cart.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService ],
  styleUrls: [ './shopping-cart.component.scss' ]
})
export class ShoppingCartComponent implements OnInit {

  owner: UserProfile;  cart: Observable<ShoppingCart>;

  constructor(private userProfileService: UserProfileService,private cartService: ShoppingCartService, private afAuth: AngularFireAuth) { }

  ngOnInit() {
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
      this.cartService.removeTxnFromCart(txn);
  }
}
