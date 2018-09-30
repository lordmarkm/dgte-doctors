import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment'

@Component({
  selector: 'app-login',
  templateUrl: './firebase-login.component.html',
  styleUrls: ['./login.component.scss'],
  providers:[ HttpClient, HttpClient ],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent {
  public router: Router;
  public form:FormGroup;
  public email:AbstractControl;
  public password:AbstractControl;
  public error: string;

  constructor(router:Router, fb:FormBuilder, public afAuth: AngularFireAuth, public http: HttpClient) {
      this.router = router;
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
        alert('Error! ' + e);
        this.error = e;
      });
    //this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider());
  }
  logout() {
    this.afAuth.auth.signOut();
  }
  checkXpayAuth() {
    this.http.get<any>(environment.url + '/auth').subscribe(r => {});
  }
  tryGetFromSecuredUrl() {
    this.http.get<any>(environment.ampUrl + '/api/user-profile').subscribe(r => {
      alert('Authenticated user: ' + r.username);
    }, err => {
      if (err.status == 404) {
        //New users have no registered user-profile so we will get a 404 response
        console.log('404! new user!');
        this.router.navigate(['/amp/onboard']);
      } else {
        //Other errors let the global exception handler handle it
        throw err;
      }
    });
  }
}

export function emailValidator(control: FormControl): {[key: string]: any} {
    var emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;    
    if (control.value && !emailRegexp.test(control.value)) {
        return {invalidEmail: true};
    }
}
