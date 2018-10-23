import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { ShoppingCart } from './shopping-cart.model';
import { Transaction, Order } from './shopping-cart.model';

const CART_STORAGE_KEY = 'ampota-shopping-cart';

@Injectable({
  //singleton
  providedIn: 'root',
})
export class ShoppingCartService {

  private _cart: BehaviorSubject<ShoppingCart> = new BehaviorSubject<ShoppingCart>(new ShoppingCart());
  public cart: Observable<ShoppingCart> = this._cart.asObservable();

  constructor() {
      let memcartStr = sessionStorage.getItem(CART_STORAGE_KEY);
      if (memcartStr) {
          console.log('Retrieved cart from local storage. cart=' + memcartStr);
          let memcart: ShoppingCart = JSON.parse(memcartStr);
          this._cart.next(memcart);
      }
  }

  private persist(cart: ShoppingCart) {
      sessionStorage.setItem(CART_STORAGE_KEY, JSON.stringify(cart));
  }
  public get(): Observable<ShoppingCart> {
      return this.cart;
  }
  public removeTxnFromCart(txn: Transaction) {
      let cart: ShoppingCart = this._cart.value;
      cart.txns = cart.txns.filter(t => t.seller != txn.seller);
      this.persist(cart);
  }
  public removeOrderFromCart(order: Order) {
      let cart: ShoppingCart = this._cart.value;
      cart.txns.forEach(txn => {
          txn.orders = txn.orders.filter(order => order.bundle.id != order.bundle.id);
      });
      cart.txns = cart.txns.filter(txn => txn.orders.length);
      this.persist(cart);
  }
  public addToCart(order: Order) {
    let cart: ShoppingCart = this._cart.value;
    let txn: Transaction = this.getTransaction(order.bundle.owner);
    
    if (!txn) {
      txn = new Transaction();
      txn.orders = [order];
      txn.seller = order.bundle.owner;
      txn.sellerName = order.bundle.ownerName;
      this.computeTotal(txn);
      cart.txns.push(txn);
    } else {
      this.addOrReplaceOrder(txn, order);
    }

    this.persist(cart);
    this._cart.next(cart);
  }

  private getTransaction(ownerName: string) {
    let cart = this._cart.value;
    return cart.txns.find(txn => txn.seller === ownerName);
  }
  private addOrReplaceOrder(txn: Transaction, order: Order) {
      txn.orders = txn.orders.filter(o => o.bundle.id != order.bundle.id);
      txn.orders.push(order);
      this.computeTotal(txn);
  }
  private computeTotal(txn: Transaction) {
      if (typeof txn.computeTotal !== 'function') {
          txn.computeTotal = function () {
              this.total = this.orders.reduce((total, order) => total + order.price, 0);
          }
      }
      txn.computeTotal();
  }

}
