import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SERVER_API_URL } from '../../app.constants';
import { Observable } from 'rxjs';
import { ExchangeRequest } from '../../model/exchange/exchangeRequest';
import { ExchangeResponse } from '../../model/exchange/exchangeResponse';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  constructor(private http: HttpClient) {
  }

  getExchangePrice(request: ExchangeRequest): Observable<any> {
    return this.http.post(`${SERVER_API_URL}/exchange`, request);
  }

}
