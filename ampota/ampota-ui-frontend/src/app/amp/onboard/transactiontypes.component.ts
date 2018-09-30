import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OnboardService } from './onboard.service';
import { ShippingProvider } from '@app/amp/shipping-provider/shipping-provider.model';
import { ShippingProviderService } from '@app/amp/shipping-provider/shipping-provider.service';
import { Meetup } from '@app/amp/meetup/meetup.model';
import { MeetupService } from '@app/amp/meetup/meetup.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';

@Component({
  templateUrl: './transactiontypes.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ ShippingProviderService, MeetupService ]
})
export class TransactionTypesComponent implements OnInit {

  userProfile: UserProfile;
  shippingProviders: ShippingProvider[];
  shippingProvidersMap: any;
  meetups: Meetup[];
  includedMeetups: Meetup[];

  constructor(public router:Router, public onboardService: OnboardService,
    public shippingProviderService: ShippingProviderService,
    public meetupService: MeetupService,
    public afAuth: AngularFireAuth) {

    if (!this.onboardService.getUserProfile()) {
      console.info("Couldn't find user profile in onboard service! returning to step 1, id=" + this.onboardService.id);
      //TODO uncomment this, delete the following line
      //router.navigate(['/amp/onboard']);
      this.onboardService.setUserProfile(new UserProfile());
    }
    this.shippingProvidersMap = new Map<number, string>();
  }

  ngOnInit() {
    this.userProfile = this.onboardService.getUserProfile();
    this.afAuth.authState.subscribe(auth => {
      console.debug('auth!');
      console.debug(auth);
      this.shippingProviderService.get('', '100').subscribe(page => {
        this.shippingProviders = page.content;
        this.initializeShippingProviderCheckboxes();
      });
      this.meetupService.get('', '100').subscribe(page => {
        this.meetups = page.content;
        this.initializeMeetupTags();
      });
    });
  }

  initializeShippingProviderCheckboxes() {
    let vm = this;
    vm.shippingProviders.forEach(function (sp) {
      vm.shippingProvidersMap[sp.id] = false;
    });

    //If userprofile has no shipping providers, instantiate an empty array
    if (!vm.userProfile.shippingProviders) {
      vm.userProfile.shippingProviders = [];
    }
    //Mark as true on the map all the shipping providers that the user already accepts. This will prepopulate the map
    vm.userProfile.shippingProviders.forEach(function (usp) {
      vm.shippingProvidersMap[usp.id] = true;
    });
  }

  initializeMeetupTags() {
    if (!this.userProfile.meetups) {
      this.userProfile.meetups = [];
    }
    this.includedMeetups = this.userProfile.meetups;
  }

  next() {
    //Handle shipping providers TODO validate at least 1 SP if SP enabled
    //first clear the existing list of SPs so we can repopulate it with the newly selected list of SPs
    this.userProfile.shippingProviders = [];

    //map looks like this: { 1: true, 2: false } so we look for shipping provider w/ id 1 and add it to user's accepted SPs
    for (let id in this.shippingProvidersMap) {
      let include = this.shippingProvidersMap[id];
      if (include) {
        let includedSp = this.shippingProviders.find((sp) => { return sp.id === parseInt(id); });
        this.userProfile.shippingProviders.push(includedSp);
      }
    }

    //Handle meetups TODO validate at least 1 meetup if meetups enabled
    this.includedMeetups.forEach(function (mu) {
      //This is hackish, but we can know that the meetup was added and not selected from the existing list if its address is blank, since addresses for
      //new meetups will be added at the backend on save
      //So here we set their ids to 0 so that the backend knows it's a new entity and not an edit to an existing one
      if (!mu.address) {
        mu.id = 0;
      }
    });
    this.userProfile.meetups = this.includedMeetups;

    this.onboardService.setUserProfile(this.userProfile);
    this.router.navigate(['/amp/onboard/accepted-currencies']);
  }
}
