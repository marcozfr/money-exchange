import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { SERVER_API_URL } from '../../app.constants';
import { Observable, interval } from 'rxjs';
import { ExchangeRequest } from '../../model/exchange/exchange-request.model';
import { ExchangeResponse } from '../../model/exchange/exchange-response.model';
import { DEFAULT_ORIGIN_CURRENCY } from '../../app.constants';
import { DEFAULT_DEST_CURRENCY } from '../../app.constants';
import { switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  private lastExchangeResponse : ExchangeResponse;

  constructor(private http: HttpClient) {
    //this.reloadExchangePrice();
  }

  buildExchangeRequest(origin: string, destination: string) : ExchangeRequest {
    let currentDate = new Date();
    let dateString = currentDate.getFullYear() + "-" +(currentDate.getMonth() + 1) + "-" + currentDate.getDate();
    const exchangeRequest = new ExchangeRequest();
    exchangeRequest.origin = origin;
    exchangeRequest.destination = destination;
    exchangeRequest.exchangeDate = dateString;
    return exchangeRequest;
  }

  getLatestExchangePrice(origin: string, destination: string): Observable<any> {
    const exchangeRequest = this.buildExchangeRequest(origin, destination);
    return this.http.post(`${SERVER_API_URL}/exchange`, exchangeRequest);
  }

  // reloadExchangePrice() {
  //    interval(5000).pipe(
  //     switchMap(() => {
  //       return this.getLatestExchangePrice(DEFAULT_ORIGIN_CURRENCY,DEFAULT_DEST_CURRENCY);
  //     })
  //   ).subscribe(data => {
  //       this.lastExchangeResponse = data;
  //   });
  // };


}
