import { Component, OnInit } from '@angular/core';
import { ExchangeService } from '../../services/exchange/exchange.service';
import { CurrencyService } from '../../services/currency/currency.service';
import { Observable } from 'rxjs';
import { ExchangeRequest } from '../../model/exchange/exchange-request.model';
import { ExchangeResponse } from '../../model/exchange/exchange-response.model';
import { CurrencyMap } from '../../model/currency/currency-map.model';
import { DEFAULT_ORIGIN_CURRENCY } from '../../app.constants';
import { DEFAULT_DEST_CURRENCY } from '../../app.constants';
import { map } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import createNumberMask from 'text-mask-addons/dist/createNumberMask';

@Component({
  selector: 'app-quotation',
  templateUrl: './quotation.component.html',
  styleUrls: ['./quotation.component.scss']
})
export class QuotationComponent implements OnInit {
  currencyMap : CurrencyMap = new CurrencyMap();
  quotationForm: FormGroup;
  submitted = false;
  controlsLoaded = false;
  message = '';
  origin = DEFAULT_ORIGIN_CURRENCY;
  destination = DEFAULT_DEST_CURRENCY;
  convertedAmount;

  constructor(
    private exchangeService : ExchangeService,
    private currencyService : CurrencyService,
    private formBuilder: FormBuilder) {   }

  ngOnInit() {
    this.quotationForm = this.formBuilder.group({
        amount: ['', Validators.required]
    });
    this.currencyService.getCurrenciesList().subscribe(data => {
      for(let i in data){
        data[i].currencyMask = createNumberMask({
          prefix: `${data[i].currencySymbol} `,
          suffix: '',
          includeThousandsSeparator: true,
          allowDecimal: true,
          decimalLimit: 4,
          requireDecimal: true,
          allowNegative: false,
          allowLeadingZeroes: false
        });
        this.currencyMap[data[i].currencyCode] = data[i];
        this.controlsLoaded = true;
      }
    });

  }

  onSubmit() {
    this.submitted = true;
    this.message = undefined;

    if (this.quotationForm.invalid) {
        return;
    }

    this.exchangeService.getLatestExchangePrice(this.origin, this.destination)
      .subscribe(response => {
        this.calculateQuotation(response);
      }, error => {
        if(error.status == '0'){
            this.message = 'Service unavailable';
        }else{
            this.message = error.error.message;
        }
      });

  }

  calculateQuotation(exchangeResponse : ExchangeResponse){
    let amount = this.quotationForm.controls.amount.value.slice(2);
    this.convertedAmount = exchangeResponse.exchangeRate * parseFloat(amount);
  }



}
