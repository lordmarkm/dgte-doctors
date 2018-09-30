import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { OnboardService } from './onboard.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { UserProfile, Address } from '@app/amp/user-profile/user-profile.model';

@Component({
  selector: 'app-onboard',
  templateUrl: './primary-shipping-address.component.html',
  encapsulation: ViewEncapsulation.None
})
export class PrimaryShippingAddressComponent implements OnInit {
  address: Address;
  userProfile : UserProfile;

  constructor(public router:Router, public onboardService: OnboardService,
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
    if (this.userProfile.addresses && this.userProfile.addresses.length) {
      this.address = this.userProfile.addresses[0];
    } else {
      this.userProfile.addresses = [];
      this.address= new Address();
      this.address.primary = true;
      this.address.country = 'Philippines';
      this.userProfile.addresses.push(this.address);
    }
  }

  next() {
    this.router.navigate(['/amp/onboard/thank-you']);
  }
}
