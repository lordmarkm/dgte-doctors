import { Component, ViewEncapsulation, Input, OnInit } from '@angular/core';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { ShoppingCart } from '@app/amp/shopping-cart/shopping-cart.model';
import { ShoppingCartService } from '@app/amp/shopping-cart/shopping-cart.service';
import { Observable } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'checkout-component',
  templateUrl: './checkout.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService ],
  styleUrls: [ './checkout.component.scss' ]
})
export class CheckoutComponent implements OnInit{

    cart: Observable<ShoppingCart>;

    constructor(private userProfileService: UserProfileService, private cartService: ShoppingCartService, private afAuth: AngularFireAuth) { }

    ngOnInit() {
        this.cart = this.cartService.cart;
    }

    verifyAndCheckout() {
      console.log('verifyAndCheckout()');
    }
}
