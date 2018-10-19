import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { UserProfile } from './user-profile.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class UserProfileService {
    public url = environment.ampUrl + "/api/user-profile";

    constructor(public http:HttpClient) { }

    public getCurrentUser(): Observable<UserProfile> {
      return this.http.get<UserProfile>(this.url);
    }

  public get(fbLink?: string): Observable<UserProfile> {
        console.debug('Getting user profile!');
        return this.http.get<UserProfile>(this.url, { params: { fbLink: fbLink } });
    }

    public save(userProfile: UserProfile) {
        console.debug('Save request! userProfile=' + JSON.stringify(userProfile));
        return this.http.post(this.url, userProfile);
    }

    public findByUsername(username: string) {
      console.info('Find by username: ' + username);
      return this.http.get<UserProfile>(this.url + '/find-by-username', { params: { username: username } });
    }

} 