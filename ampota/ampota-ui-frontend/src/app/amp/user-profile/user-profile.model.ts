import { Bank } from '@app/amp/bank/bank.model';
import { ShippingProvider } from '@app/amp/shipping-provider/shipping-provider.model';
import { Meetup } from '@app/amp/meetup/meetup.model';

export class UserProfile {
  username: string;
  contactNumber: string;
  addresses: Address[];
  shipping: boolean;
  shippingProviders: ShippingProvider[];
  meetup: boolean;
  meetups: Meetup[];
  meetupNote: string;
  ampotaCoin: boolean;
  cod: boolean;
  bankDeposit: boolean;
  bankAccounts: BankAccount[];
  permissions: string[];
  fbLink: string;
  constructor(json?: any) {
    if (json) {
      this.username = json.username;
      this.permissions = json.permissions;
    } else {
      console.warn('Could not parse user properties from json');
    }
  }
  hasRole(role: string) {
    return true;
  }
}

export class Address {
    primary: boolean;
    line?: string;
    firstName?: string;
    lastName?: string;
    line1?: string;
    line2?: string;
    city?: string;
    country?: string;
    postalCode?: string;
}

export class BankAccount {
    primary: boolean;
    bank: Bank;
    accountName: string;
    accountNumber: string;
}

