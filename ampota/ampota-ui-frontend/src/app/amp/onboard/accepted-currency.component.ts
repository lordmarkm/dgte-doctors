import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OnboardService } from './onboard.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { UserProfile, BankAccount } from '@app/amp/user-profile/user-profile.model';
import { Bank } from '@app/amp/bank/bank.model';
import { BankService } from '@app/amp/bank/bank.service';

@Component({
  templateUrl: './accepted-currency.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ BankService ]
})
export class AcceptedCurrencyComponent implements OnInit {
  userProfile: UserProfile;
  banks: Bank[];
  bankAccount: BankAccount;

  constructor(public router:Router, public onboardService: OnboardService,
    public bankService: BankService,
    public afAuth: AngularFireAuth) {
    if (!this.onboardService.getUserProfile()) {
      console.info("Couldn't find user profile in onboard service! returning to step 1, id=" + this.onboardService.id);
      //TODO uncomment this, delete the following line
      //router.navigate(['/amp/onboard']);
      this.onboardService.setUserProfile(new UserProfile());
    }
  }

  ngOnInit() {
    this.userProfile = this.onboardService.getUserProfile();
    if (this.userProfile.bankAccounts && this.userProfile.bankAccounts.length) {
      this.bankAccount = this.userProfile.bankAccounts[0];
    } else {
      //set ampota coin enabled to true
      this.userProfile.ampotaCoin = true;

      this.userProfile.bankAccounts = [];
      this.bankAccount = new BankAccount();
      this.bankAccount.primary = true;
      this.userProfile.bankAccounts.push(this.bankAccount);
    }

    this.afAuth.authState.subscribe(auth => {
      console.debug('auth!');
      console.debug(auth);
      this.bankService.get('', '100').subscribe(page => {
        this.banks = page.content;
      });
    });
  }

  //Used by the bank select
  compareById(bank1: Bank, bank2: Bank): boolean {
    return bank1 && bank2 && bank1.id === bank2.id;
  }

  next() {
    this.router.navigate(['/amp/onboard/shipping-address']);
  }
}
