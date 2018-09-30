import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';

@Injectable()
export class AddFirebaseTokenInterceptor implements HttpInterceptor {

  constructor(public afAuth: AngularFireAuth) {
    this.afAuth = afAuth;
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.indexOf('imgur') != -1) {
      return next.handle(req);
    }
    let accessToken = '';
    try {
      accessToken = JSON.parse(JSON.stringify(this.afAuth.auth.currentUser)).stsTokenManager.accessToken;
    } catch (e) {
      console.error('AddFirebaseTokenInterceptor: Unable to parse firebase access token!');
      //do nothing, leave as blank
    }
    console.debug('Adding header for auth: ' + JSON.stringify(this.afAuth.auth.currentUser));
    // Clone the request to add the new header
    const clonedRequest = req.clone({ headers: req.headers.set('X-Firebase-Auth', accessToken) });

    // Pass the cloned request instead of the original request to the next handle
    return next.handle(clonedRequest);
  }
}
