export class Page {
  totalPages: number;
  totalElements: number;
  content: ShippingProvider[];
}

export class ShippingProvider {
  id: number;
  name: string;
  imageUrl: string;
}
