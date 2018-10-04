import { Observable } from 'rxjs';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { SERVER_API_URL } from '../../app.constants';

export class AuthInterceptor implements HttpInterceptor {
    constructor() {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        console.log('On interceptor');

        if (!request || !request.url || (/^http/.test(request.url)
            && !(SERVER_API_URL && request.url.startsWith(SERVER_API_URL)))) {
            return next.handle(request);
        }

        if(request.url.startsWith(`${SERVER_API_URL}/auth`)){
            return next.handle(request);
        }

        const currentUser = sessionStorage.getItem('currentUser') || sessionStorage.getItem('currentUser');
        if (!!currentUser) {
            const currentUserObj = JSON.parse(currentUser);
            request = request.clone({
                setHeaders: {
                    Authorization : `Bearer ${currentUserObj.token}`
                }
            });
        }
        return next.handle(request);
    }
}
