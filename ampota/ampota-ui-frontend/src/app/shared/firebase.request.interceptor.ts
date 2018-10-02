import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';
import { mergeMap } from 'rxjs/operators';

@Injectable()
export class AddFirebaseTokenInterceptor implements HttpInterceptor {

  constructor(private afAuth: AngularFireAuth) {
    this.afAuth = afAuth;
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.indexOf('imgur') != -1) {
      return next.handle(req);
    }
    let accessToken = '';
    try {
      let t: Observable<string> = from(this.afAuth.auth.currentUser.getIdToken());
      return t.pipe(mergeMap((token: string) => {
        console.log('retrieved firebase token: ' + token);

        // Clone the request to add the new header
        const clonedRequest = req.clone({ headers: req.headers.set('X-Firebase-Auth', token) });

        // Pass the cloned request instead of the original request to the next handle
        return next.handle(clonedRequest);
      }));
    } catch (e) {
      console.error('AddFirebaseTokenInterceptor: Unable to parse firebase access token!');
      //do nothing, leave as blank
      throw e;
    }
  }
}
