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
}

export class Order {
  bundle: Bundle;
  qty: number;
  price: number;
}
