import { Injectable } from '@angular/core';
import { UserProfile } from '@app/amp/user-profile/user-profile.model.ts';

@Injectable({
  providedIn: 'root',
})
export class OnboardService {

  userProfile: UserProfile;
  id: string = Math.random().toString(36).slice(2);

  constructor() { }

  public getUserProfile() {
    return this.userProfile;
  }
  public setUserProfile(userProfile: UserProfile) {
    this.userProfile = userProfile;
  }

}
