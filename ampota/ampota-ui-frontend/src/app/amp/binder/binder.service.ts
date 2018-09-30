import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page, Binder } from './binder.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class BinderService {
    public url = environment.ampUrl + "/api/binder";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting binders!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(binder: Binder) {
        console.debug('Save request! binder=' + JSON.stringify(binder));
        return this.http.post(this.url, binder);
    }

} 