export class Page {
  totalPages: number;
  totalElements: number;
  content: Binder[];
}

export class Binder {
    owner: string;
    name: string;
    bulkPurchaseAmt: number;
}
