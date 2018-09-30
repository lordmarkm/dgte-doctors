import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page, Project } from './project.model';
import { environment } from '../../../environments/environment'

@Injectable({
  //this makes this service a singleton
  providedIn: 'root'
})
export class ProjectService {
    public url = environment.url + "/api/project";
	public static events;
	public eventSubject;
	public events;

    constructor(public http:HttpClient) {
      console.debug('initializing project service');
      this.eventSubject = new Subject();
      this.events = this.eventSubject.asObservable();
      this.events.subscribe(event => {
        console.info('Event!');
      });
    }

    getProjects(term: string): Observable<Page> {
        console.debug('Getting projects!');
        let params = new HttpParams().set('term', term);
        return this.http.get<Page>(this.url, { params: params });
    }

    public save(project: Project) {
        console.debug('Save request! project=' + JSON.stringify(project));

        //please see class annotations of com.dgtedr.project.shared.dto.BaseInfo
        project.type = 'project';

        let isNew = null != project.id;
        this.http.post(this.url, project).subscribe(e => {
          console.debug('Save return value:');
          console.debug(e);
          let type = isNew ? 'add' : 'edit';
          this.eventSubject.next({type: type, project: project});
        });


    }

} 