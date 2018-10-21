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
      let cart: ShoppingCart = this._cart.value;
      cart.txns = cart.txns.filter(t => t.seller != txn.seller);
  }
  public removeOrderFromCart(order: Order) {
      let cart: ShoppingCart = this._cart.value;
      cart.txns.forEach(txn => {
          txn.orders = txn.orders.filter(order => order.bundle.id != order.bundle.id);
      });
      cart.txns = cart.txns.filter(txn => txn.orders.length);
  }
  public addToCart(order: Order) {
    console.log('Adding to cart');
    let cart: ShoppingCart = this._cart.value;
    let txn: Transaction = this.getTransaction(order.bundle.owner);
    
    if (!txn) {
      txn = new Transaction();
      txn.orders = [order];
      txn.seller = order.bundle.owner;
      txn.sellerName = order.bundle.ownerName;
      cart.txns.push(txn);
    } else {
      this.addOrReplaceOrder(txn, order);
    }

    this._cart.next(cart);
  }

  private getTransaction(ownerName: string) {
    let cart = this._cart.value;
    return cart.txns.find(txn => txn.seller === ownerName);
  }
  private addOrReplaceOrder(txn: Transaction, order: Order) {
      txn.orders = txn.orders.filter(o => o.bundle.id != order.bundle.id);
      txn.orders.push(order);
  }
  public get(): Observable<ShoppingCart> {
    return this.cart;
  }

}
