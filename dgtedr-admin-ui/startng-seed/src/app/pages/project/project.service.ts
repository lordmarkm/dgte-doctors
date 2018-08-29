import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page } from './project.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class ProjectService {
    public url = environment.url + "/api/project";
    private eventSubject = new Subject();
    public events = this.eventSubject.asObservable();

    constructor(public http:HttpClient) { }

    getProjects(term: string): Observable<Page> {
        console.debug('Getting projects!');
        let params = new HttpParams().set('term', term);
        this.eventSubject.next({ msg: 'hello world' });
        return this.http.get<Page>(this.url, { params: params });
    }

} 