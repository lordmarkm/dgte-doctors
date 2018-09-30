import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, KnowYourCustomer } from './kyc.model';
import { environment } from '../../../environments/environment'

@Injectable({
  //this makes this service a singleton
  providedIn: 'root'
})
export class KnowYourCustomerService {
    public url = environment.url + "/api/kyc";

    constructor(public http: HttpClient) {
      console.debug('initializing kyc service');
    }

    getKyc(term: string): Observable<Page> {
        console.debug('Getting kyc!');
        let params = new HttpParams().set('term', term);
        return this.http.get<Page>(this.url, { params: params });
    }

}
