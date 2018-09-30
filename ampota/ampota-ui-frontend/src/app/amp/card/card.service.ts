import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page, Card } from './card.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class CardService {
    public url = environment.ampUrl + "/api/card";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting cards!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public getUniqueNames(term: string, size: string): Observable<string[]> {
        console.debug('Getting unique card names!');
        let params = new HttpParams()
          .set('term', term);
        return this.http.get<string[]>(this.url + '/unique-names', { params: params });
    }
}
