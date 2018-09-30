export class Page {
  totalPages: number;
  totalElements: number;
  content: Meetup[];
}

export class Meetup {
  id: number;
  name: string;
  address: Address;
}

export class Address {
  primary: boolean = false;
  firstName?: string;
  lastName?: string;
  line1?: string;
  line2?: string;
  city?: string;
  country?: string;
  postalCode?: string;
}
