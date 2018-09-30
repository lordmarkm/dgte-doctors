import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, Audit } from './audit.model';
import { environment } from '../../../environments/environment'

@Injectable({
  //this makes this service a singleton
  providedIn: 'root'
})
export class AuditService {
    public url = environment.url + "/api/audit";

    constructor(public http: HttpClient) {
      console.debug('initializing audit service');
    }

    getAudit(term: string): Observable<Page> {
        console.debug('Getting audit!');
        let params = new HttpParams().set('term', term);
        return this.http.get<Page>(this.url, { params: params });
    }

}
