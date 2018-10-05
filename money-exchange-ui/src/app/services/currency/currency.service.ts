import { Injectable } from '@angular/core';
import { SERVER_API_URL } from '../../app.constants';
import { Currency } from '../../model/currency/currency.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient) {
  }

  getCurrenciesList(): Observable<Object> {
    return this.http.get(`${SERVER_API_URL}/currencies`);
  }

}
