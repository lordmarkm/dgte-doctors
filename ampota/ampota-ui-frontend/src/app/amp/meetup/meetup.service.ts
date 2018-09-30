import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, Meetup } from './meetup.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class MeetupService {
    public url = environment.ampUrl + "/api/meetup";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting meetups!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(meetup: Meetup) {
        console.debug('Save request! meetup=' + JSON.stringify(meetup));
        return this.http.post(this.url, meetup);
    }

} 