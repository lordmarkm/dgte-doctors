import { Bundle } from '@app/amp/bundle/bundle.model';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';

export class ShoppingCart {
  txns: Transaction[] = [];
}

export class Transaction {
    buyer: string;
    seller: string;
    sellerName: string;
    orders: Order[];
    total: number;
    public computeTotal() {
        this.total = this.orders.reduce((total, order) => total + order.price, 0);
    }
}

export class Order {
  bundle: Bundle;
  qty: number;
  price: number;
}
