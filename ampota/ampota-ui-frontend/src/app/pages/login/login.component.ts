import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase';
import { environment } from '../../../environments/environment';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './firebase-login.component.html',
  styleUrls: ['./login.component.scss'],
  providers:[ UserProfileService ],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  public error: string;
  public loading: boolean = false;
  public fbLink: string;

  constructor(private http: HttpClient, private router:Router, private afAuth: AngularFireAuth, private userProfileService: UserProfileService) { }

  ngOnInit() {
    this.loading = true;
    this.afAuth.authState.subscribe(auth => {
      console.log('auth complete');
      if (auth) {
        this.ampAuthorize();
      } else {
        this.error = 'Please sign in with Firebase';
        this.loading = false;
      }
    },
    err => {
      this.error = 'Unable to authenticate with firebase: ' + err;
      this.loading = false;
    });
  }
  ngAfterViewInit(){
      document.getElementById('preloader').classList.add('hide');
  }
  login() {
    this.loading = true;
    let fb = new auth.FacebookAuthProvider();
    fb.addScope('user_link');
    this.afAuth.auth.signInWithPopup(fb)
      .then((auth: any) => {
        delete this.error;
        let fbToken = auth.credential.accessToken;
        this.http.get<any>('https://graph.facebook.com/me?access_token=' + fbToken + '&fields=link').subscribe(fbDetails => {
          console.log('sending fb_link request');
          if (fbDetails.link) {
            this.http.put<any>(environment.ampUrl + '/api/fb-link?fbLink=' + fbDetails.link, {}).subscribe(r => r);
          }
        });
        this.loading = false;
      },
      err => {
        this.error = 'Login failed: ' + err;
        this.loading = false;
      });
  }

  private ampAuthorize() {
    this.loading = true;
    this.userProfileService.get(this.fbLink).subscribe(user => {
      let userProfile: UserProfile = new UserProfile(user);
      if (userProfile.hasRole('ROLE_ADMIN')) {
        //admin user authenticated
        //this.router.navigate(['/amp/admin-dashboard']);
        this.router.navigate(['/amp/dashboard']);
      } else {
        //non-admin user authenticated
        this.router.navigate(['/amp/dashboard']);
      }
      this.loading = false;
    },
    err => {
      //User menu component could not get xpay user from Firebase authentication
      switch(err.status) {
      case 404:
        this.router.navigate(['/amp/onboard']);
        break;
      default:
        this.error = 'Authentication error. err=' + err.message;
      }
      this.loading = false;
    });
  }

}
