import { Binder } from '@app/amp/binder/binder.model';
import { Card } from '@app/amp/card/card.model';

export class Page {
  totalPages: number;
  totalElements: number;
  content: Bundle[];
}

export class Bundle {
    id: number;
    owner: string;
    binder?: Binder;
    card: Card;
    qty: number;
    sellPrice: number;
    sellPriceSet?: number;
    boughtPrice?: number;
    condition: string;
    forSale: boolean;
    sellMode: string;
    pictures?: string[];
}
