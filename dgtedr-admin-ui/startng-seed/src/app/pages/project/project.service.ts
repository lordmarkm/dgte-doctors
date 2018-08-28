import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page } from './project.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class ProjectService {
    public url = environment.url + "/api/project";
    constructor(public http:HttpClient) { }

    getProjects(term: String): Observable<Page> {
        console.debug('Getting projects!');
        let params = new HttpParams().set('term', term);
        return this.http.get<Page>(this.url, { params: params });
    }

} 