import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UserProfile } from './user-profile.model';
import { UserProfileService } from './user-profile.service';
import { AngularFireAuth } from '@angular/fire/auth';
import * as moment from 'moment';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  providers: [ UserProfileService ],
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit{
  userProfile: UserProfile = new UserProfile();
  displayName: string;
  imageUrl: string;
  paymentModes: string;
  banks: string;
  primaryShippingAddress: string;

  constructor(public userProfileService: UserProfileService, public afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.afAuth.authState.subscribe(auth => {
      console.log(auth);
      this.imageUrl = auth.photoURL;
      this.displayName = auth.displayName;
      this.userProfileService.get().subscribe(u => { 
        this.userProfile = u;
        this.evaluatePaymentModes();
        this.evaluateBanks();
        this.evaluatePrimaryShippingAddress();
      });
    });
  }

  evaluatePaymentModes() {
    let pms: string[] = [];
    if (this.userProfile.ampotaCoin) {
      pms.push('Ampota Coin');
    }
    if (this.userProfile.bankDeposit) {
      pms.push('Bank Deposit');
    }
    this.paymentModes = pms.join(', ');
  }

  evaluateBanks() {
    let banks: string[] = [];
    if (!this.userProfile.bankDeposit) {
      this.banks = 'None';
    } else {
      this.userProfile.bankAccounts.forEach(bankAccount => { banks.push(bankAccount.bank.name); });
      this.banks = banks.join(', ');
    }
  }

  evaluatePrimaryShippingAddress() {
    let addr = this.userProfile.addresses.find(a => { return a.primary; });
    this.primaryShippingAddress = addr.line || 'No address defined';
  }

  momento(m) {
    return moment(m);
  }
}
