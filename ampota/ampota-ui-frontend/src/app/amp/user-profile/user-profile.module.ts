import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserProfileComponent } from './user-profile.component';
import { FormsModule } from '@angular/forms';

export const routes = [
  { path: '', component: UserProfileComponent, pathMatch: 'full' },
  { path: '/contact-details', component: UserProfileComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    UserProfileComponent,
  ]
})

export class UserProfileModule { }
