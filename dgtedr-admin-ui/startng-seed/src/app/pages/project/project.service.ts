import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, Project } from './project.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class ProjectService {
    public url = environment.url + "/api/project";
    private eventSubject = new Subject();
    public events = this.eventSubject.asObservable();

    constructor(public http:HttpClient) {
      this.events.subscribe(event => {
        console.info('Event!');
      });
    }

    getProjects(term: string): Observable<Page> {
        console.debug('Getting projects!');
        let params = new HttpParams().set('term', term);
        this.eventSubject.next('hello world');
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(project: Project) {
        console.debug('Save request! project=' + JSON.stringify(project));

        //please see class annotations of com.dgtedr.project.shared.dto.BaseInfo
        project.type = 'project';

        return this.http.post(this.url, project).subscribe(e => {
          console.debug(e);
        });
    }

} 