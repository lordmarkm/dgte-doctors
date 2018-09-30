import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, Bank } from './bank.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class BankService {
    public url = environment.ampUrl + "/api/bank";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting banks!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(bank: Bank) {
        console.debug('Save request! bank=' + JSON.stringify(bank));
        return this.http.post(this.url, bank);
    }

} 