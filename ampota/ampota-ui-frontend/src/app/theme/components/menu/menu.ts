import { Menu } from './menu.model';

export const verticalMenuItems = [ 
    new Menu (1, 'Dashboard', '/pages/dashboard', null, 'tachometer', null, false, 0),
    new Menu (2, 'Audit Logs', '/pages/audit', null, 'file-o', null, false, 0),
    new Menu (3, 'KYC Approval', '/pages/kyc', null, 'file-o', null, false, 0),
    new Menu (4, 'AMP', null, null, 'file-text-o', null, true, 0),
    new Menu (41, 'Login', '/login', null, 'file-o', null, false, 4),
    new Menu (42,'Onboard', null, null, 'file-o', null, true, 4),
    new Menu (421, 'Welcome', '/amp/onboard', null, 'file-o', null, false, 42),
    new Menu (426, 'Contact Details', '/amp/onboard/contact-info', null, 'file-o', null, false, 42),
    new Menu (422, 'Transaction Types', '/amp/onboard/transaction-types', null, 'file-o', null, false, 42),
    new Menu (423, 'Accepted Currencies', '/amp/onboard/accepted-currencies', null, 'file-o', null, false, 42),
    new Menu (424, 'Primary Shipping Address', '/amp/onboard/shipping-address', null, 'file-o', null, false, 42),
    new Menu (43, 'User Profile', '/amp/user-profile', null, 'file-o', null, false, 4),
    new Menu (425, 'Thank you', '/amp/onboard/thank-you', null, 'file-o', null, false, 42),
    new Menu (44,'Admin', null, null, 'gears', null, true, 4),
    new Menu (444, 'Dashboard', '/amp/admin', null, 'bar-chart-o', null, false, 44),
    new Menu (441, 'Meetup List', '/amp/admin/meetup-list', null, 'group', null, false, 44),
    new Menu (442, 'Shipping Provider', '/amp/admin/shipping-provider-list', null, 'ship', null, false, 44),
    new Menu (443, 'Bank', '/amp/admin/bank-list', null, 'bank', null, false, 44),
    new Menu (45,'Collection', null, null, 'archive', null, true, 4),
    new Menu (451,'My Binders', '/amp/collection', null, 'address-book', null, false, 45),
    new Menu (452,'My Cards', '/amp/collection/bundle-list', null, 'id-card', null, false, 45),
/*    new Menu (40, 'Pages', null, null, 'file-text-o', null, true, 0),
    new Menu (43, 'Login', '/login', null, 'sign-in', null, false, 40),    
    new Menu (44, 'Register', '/register', null, 'registered', null, false, 40),
    new Menu (45, 'Blank', '/pages/blank', null, 'file-o', null, false, 40),
    new Menu (46, 'Error', '/pagenotfound', null, 'exclamation-circle', null, false, 40),   
    new Menu (47, 'Projects', '/pages/proj', null, 'file-o', null, false, 40),
    new Menu (140, 'Level 1', null, null, 'folder-open-o', null, true, 0),
    new Menu (141, 'Level 2', null, null, 'folder-open-o', null, true, 140),
    new Menu (142, 'Level 3', null, null, 'folder-open-o', null, true, 141),
    new Menu (143, 'Level 4', null, null, 'folder-open-o', null, true, 142),
    new Menu (144, 'Level 5', null, null, 'folder-o', null, false, 143),
    new Menu (200, 'External Link', null, 'http://themeseason.com', 'external-link', '_blank', false, 0),
    new Menu (1000, 'Projects', null, null, 'external-link', null, true, 0),
    new Menu (2000, 'Audit Logs', null, null, 'external-link', null, true, 0)
*/
]

export const horizontalMenuItems = [ 
    new Menu (1, 'Dashboard', '/pages/dashboard', null, 'tachometer', null, false, 0),
    new Menu (40, 'Pages', null, null, 'file-text-o', null, true, 0),
    new Menu (43, 'Login', '/login', null, 'sign-in', null, false, 0),    
    new Menu (44, 'Register', '/register', null, 'registered', null, false, 0),
    new Menu (45, 'Blank', '/pages/blank', null, 'file-o', null, false, 40),
    new Menu (46, 'Error', '/pagenotfound', null, 'exclamation-circle', null, false, 40),
    new Menu (200, 'External Link', null, 'http://themeseason.com', 'external-link', '_blank', false, 0),
    new Menu (1000, 'Projects', null, null, 'external-link', null, true, 0),
    new Menu (2000, 'Audit Logs', null, null, 'external-link', null, true, 0)
]