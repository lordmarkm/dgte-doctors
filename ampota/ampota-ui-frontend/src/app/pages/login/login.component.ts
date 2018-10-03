import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase';
import { environment } from '../../../environments/environment';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';

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

  constructor(private router:Router, private afAuth: AngularFireAuth, private userProfileService: UserProfileService) { }

  ngOnInit() {
    this.loading = true;
    this.afAuth.authState.subscribe(auth => {
      if (auth) {
        this.xpayAuthorize();
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
    this.afAuth.auth.signInWithPopup(new auth.FacebookAuthProvider())
      .then((auth) => {
        //No need for this line because this.afAuth.authState.subscribe already listens for changes in user auth status
        //this.xpayAuthorize();
        this.loading = false;
      },
      err => {
        this.error = 'Login failed: ' + err;
        this.loading = false;
      });
  }

  private xpayAuthorize() {
    this.loading = true;
    this.userProfileService.get().subscribe(user => {
      let userProfile: UserProfile = new UserProfile(user);
      if (userProfile.hasRole('ROLE_ADMIN')) {
        //admin user authenticated
        this.router.navigate(['/amp/admin-dashboard']);
      } else {
        //non-admin user authenticated
        this.router.navigate(['/amp/dashboard']);
      }
      this.loading = false;
    },
    err => {
      //User menu component could not get xpay user from Firebase authentication
      this.error = 'Authentication error. err=' + err;
      this.loading = false;
    });
  }

}
