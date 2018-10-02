import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AngularFireAuth } from '@angular/fire/auth';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';

@Injectable()
export class GlobalHttpErrorHandler implements HttpInterceptor {
 
  constructor(private router: Router) {}
 
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).do((event: HttpEvent<any>) => { /*do nothing on OK*/ },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          console.error('Http Erorr!! Status code=' + err.status);
          switch(err.status) {
              case 401:
                console.error('401! Redirecting to login page');
                this.router.navigate(['/login']);
              default:
                console.error('Unhandled error response: ' + err.status);
                throw err;
          }
        }
      });
  }
}
