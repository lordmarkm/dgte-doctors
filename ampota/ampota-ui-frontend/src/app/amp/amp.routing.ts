import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AmpComponent } from './amp.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

export const routes: Routes = [
    {
        path: '', 
        component: AmpComponent,
        children:[
            { path:'', redirectTo:'dashboard', pathMatch:'full' },
            { path: 'onboard', loadChildren: 'app/amp/onboard/onboard.module#OnboardModule', data: { breadcrumb: 'Create Account' } },
            { path: 'dashboard', loadChildren: 'app/amp/dashboard/dashboard.module#DashboardModule', data: { breadcrumb: 'Dashboard' } },
            { path: 'user-profile', component: UserProfileComponent, data: { breadcrumb: 'User Profile' } },
            { path: 'admin', loadChildren: 'app/amp/admin/admin.module#AdminModule', data : { breadcrumb: 'Admin' } },
            { path: 'collection', loadChildren: 'app/amp/collection/collection.module#CollectionModule', data : { breadcrumb: 'Collection' } },
            { path: 'market', loadChildren: 'app/amp/market/market.module#MarketModule', data: { breadcrumb: 'Market' } }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);