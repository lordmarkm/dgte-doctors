import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment'
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';

@Component({
  selector: 'app-login',
  templateUrl: './firebase-login.component.html',
  styleUrls: ['./login.component.scss'],
  providers:[ HttpClient, HttpClient, UserProfileService ],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  loading: boolean = false;
  error: string;

  constructor(private router:Router, fb:FormBuilder, private afAuth: AngularFireAuth, private http: HttpClient,
              private userProfileService: UserProfileService) {
  }

  ngOnInit() {
    this.loading = true;
    this.afAuth.authState.subscribe(auth => {
      this.http.get<any>(environment.ampUrl + '/api/user-profile').subscribe(r => {
        this.router.navigate(['/amp/dashboard']);
      }, err => {
        this.loading = false;
        if (err.status == 404) {
          //New users have no registered user-profile so we will get a 404 response
          console.log('404! new user!');
          this.router.navigate(['/amp/onboard']);
        } else {
          //Other errors let the global exception handler handle it
          console.log('non 404 error: ' + err.status);
          throw err;
        }
      }, () => {
        this.loading = false;
      });
    }, firebaseError=> {
      console.log('firebase error');
    });
  }
  ngAfterViewInit(){
      document.getElementById('preloader').classList.add('hide');
  }
  login() {
    this.afAuth.auth.signInWithPopup(new auth.FacebookAuthProvider())
      .then((credential) => {
        //console.debug(JSON.stringify(credential));
        console.log('Attempting to get user profile for: ' + credential.user.email);
      },
      e => {
        this.error = e;
      });
    //this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider());
  }
  logout() {
    this.afAuth.auth.signOut();
  }
}

export function emailValidator(control: FormControl): {[key: string]: any} {
    var emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;    
    if (control.value && !emailRegexp.test(control.value)) {
        return {invalidEmail: true};
    }
}
