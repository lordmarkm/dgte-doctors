import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, ShippingProvider } from './shipping-provider.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class ShippingProviderService {
    public url = environment.ampUrl + "/api/shipping-provider";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting shipping providers!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(shippingProvider: ShippingProvider) {
        console.debug('Save request! project=' + JSON.stringify(shippingProvider));
        return this.http.post(this.url, shippingProvider);
    }

} 