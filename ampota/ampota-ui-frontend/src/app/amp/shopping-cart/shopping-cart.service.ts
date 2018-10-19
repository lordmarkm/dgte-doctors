import { Injectable, OnInit, Inject } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { ShoppingCart } from './shopping-cart.model';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Transaction, Order } from './shopping-cart.model';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';

const CART_STORAGE_KEY: string = 'ampota-shopping-cart';

@Injectable({
  //singleton
  providedIn: 'root',
})
export class ShoppingCartService implements OnInit {

  private _cart: BehaviorSubject<ShoppingCart> = new BehaviorSubject<ShoppingCart>(new ShoppingCart());
  public cart: Observable<ShoppingCart> = this._cart.asObservable();

  constructor() { }

  ngOnInit() {
    //let memcart = this.storage.get(CART_STORAGE_KEY);
    //console.log('Retrieved cart from local storage. cart=' + this.cart);
    //if (memcart) {
    //  this._cart.next(memcart || new ShoppingCart());
    //}
  }

  public removeTxnFromCart(txn: Transaction) {
  }
  public removeOrderFromCart(order: Order) {
  }
  public addToCart(order: Order, buyer: UserProfile, seller: UserProfile) {
    console.log('Adding to cart');
    let cart: ShoppingCart = this._cart.value;
    let txn: Transaction = new Transaction();
    txn.orders = [order];
    txn.buyer = buyer;
    txn.seller = order.bundle.owner;
    txn.sellerName = order.bundle.ownerName;
    cart.txns.push(txn);
    this._cart.next(cart);
  }
  public get(): Observable<ShoppingCart> {
    return this.cart;
  }

}
