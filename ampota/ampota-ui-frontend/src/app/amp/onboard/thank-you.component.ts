import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { OnboardService } from './onboard.service';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';

@Component({
  selector: 'app-welcome',
  templateUrl: './thank-you.component.html',
  encapsulation: ViewEncapsulation.None
})
export class ThankYouComponent  {
  router: Router;
  http: HttpClient;
  onboardService: OnboardService;
  registrationUrl: string = environment.ampUrl + '/api/user-profile/register';
  loading: boolean = false;
  accountCreated: boolean = false;

  constructor(router: Router, http: HttpClient, onboardService: OnboardService) {
     this.router = router;
     this.http = http;
     this.onboardService = onboardService;
  }

  next() {
    this.loading = true;
    let userProfile: UserProfile = this.onboardService.getUserProfile();
    console.info('About to register! user profile=');
    console.info(userProfile);
    this.http.post(this.registrationUrl, userProfile).subscribe(r => {
      console.info('Account created! user profile=');
      console.info(r);
      this.accountCreated = true;
    },
    err => {
      console.error('error: ' + err.status);
      console.error(err);
      if (err.status == 409) {
        console.debug('Error 409 means this user probably already exists. Redirecting to user profile.');
        this.router.navigate(['/amp/user-profile']);
      }
      this.loading = false;
    },
    () => {
      console.info('this is the finally clause.');
      this.loading = false;
    });
  }
}
