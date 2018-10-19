import { Component, ViewEncapsulation, Input, OnInit } from '@angular/core';
import { UserProfile } from '@app/amp/user-profile/user-profile.model';
import { UserProfileService } from '@app/amp/user-profile/user-profile.service';

@Component({
  selector: 'bundle-owner-details',
  templateUrl: './bundle-owner-details.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [ UserProfileService ],
  styleUrls: [ './bundle-owner-details.component.scss' ]
})
export class BundleOwnerDetailsComponent implements OnInit{

  @Input() ownerUsername: string;
  owner: UserProfile;

  constructor(private userProfileService: UserProfileService) { }

  ngOnInit() {
    this.userProfileService.findByUsername(this.ownerUsername).subscribe(resp => this.owner = resp);
  }

}
