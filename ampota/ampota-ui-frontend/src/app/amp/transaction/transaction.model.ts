import { Transaction } from '@app/amp/shopping-cart/shopping-cart.model';

export class Page {
  totalPages: number;
  totalElements: number;
  content: Transaction[];
}
