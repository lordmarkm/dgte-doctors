import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { OnboardService } from './onboard.service';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: []
})
export class WelcomeComponent  {
  router: Router;
  onboardService: OnboardService;
  constructor(router:Router, onboardService: OnboardService, public afAuth: AngularFireAuth) {
      this.router = router;
      this.onboardService = onboardService;

      //if already processing, send to txn types page
      if (this.onboardService.userProfile) {
        this.next();
      } else {
        this.onboardService.setUserProfile(new UserProfile());
        console.debug(this.onboardService.getUserProfile() + ', id=' + this.onboardService.id);
      }
  }

  next() {
    this.router.navigate(['/amp/onboard/contact-info']);
  }
}
