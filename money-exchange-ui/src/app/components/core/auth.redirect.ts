import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthRedirect implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (sessionStorage.getItem('currentUser')) {
          console.log('logged in');
          return true;
        }
        console.log('not logged in');
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}
