import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { OnboardService } from './onboard.service';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: []
})
export class ContactComponent implements OnInit {
  router: Router;
  onboardService: OnboardService;
  contactNumber: string;
  email: string;
  constructor(router:Router, onboardService: OnboardService, public afAuth: AngularFireAuth) {
      this.router = router;
      this.onboardService = onboardService;

      console.debug(this.onboardService.getUserProfile());
      if (!this.onboardService.getUserProfile()) {
        console.info("Couldn't find user profile in onboard service! returning to step 1, id=" + this.onboardService.id);
        router.navigate(['/amp/onboard']);
      }
  }

  ngOnInit() {
    this.contactNumber = this.onboardService.getUserProfile().contactNumber;
    this.afAuth.authState.subscribe(auth => {
      console.debug('auth!');
      console.debug(auth);
      this.email = auth.email;
    });
  }

  next() {
    this.onboardService.userProfile.contactNumber = this.contactNumber;
    this.router.navigate(['/amp/onboard/transaction-types']);
  }
}
