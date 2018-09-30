import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page, Bundle } from './bundle.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class BundleService {
    public url = environment.ampUrl + "/api/bundle";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting bundles!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public findOne(id: number): Observable<Bundle> {
      console.log('Finding bundle by id: ' + id);
      return this.http.get<Bundle>(this.url + '/' + id);
    }

    public save(bundle: Bundle): Observable<Bundle> {
        console.debug('Save request! bundle=' + bundle.id);
        return this.http.post<Bundle>(this.url, bundle);
    }

    public delete(bundle: Bundle): Observable<Bundle> {
        console.debug('Delete request! bundle=' + bundle.id);
        return this.http.delete<Bundle>(this.url + '/' + bundle.id);
    }
} 