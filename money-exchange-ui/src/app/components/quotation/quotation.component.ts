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

  exchangeRequest : ExchangeRequest = new ExchangeRequest();
  exchangeResponse : ExchangeResponse = new ExchangeResponse();
  currencyMap : CurrencyMap = new CurrencyMap();
  quotationForm: FormGroup;
  submitted = false;
  currencyMask;
  eurCurrencyMask;

  constructor(
    private exchangeService : ExchangeService,
    private currencyService : CurrencyService,
    private formBuilder: FormBuilder) {   }

  ngOnInit() {
    this.quotationForm = this.formBuilder.group({
        amount: ['', Validators.required],
    });
    this.exchangeRequest.origin = DEFAULT_ORIGIN_CURRENCY;
    this.exchangeRequest.destination = DEFAULT_DEST_CURRENCY;
    this.currencyMask = createNumberMask({
      prefix: '$ ',
      suffix: '',
      includeThousandsSeparator: true,
      allowDecimal: true,
      decimalLimit: 4,
      requireDecimal: true,
      allowNegative: false,
      allowLeadingZeroes: false
    });
    this.eurCurrencyMask = createNumberMask({
      prefix: '€ ',
      suffix: '',
      includeThousandsSeparator: true,
      allowDecimal: true,
      decimalLimit: 4,
      requireDecimal: true,
      allowNegative: false,
      allowLeadingZeroes: false
    });
    // this.currencyService.getCurrenciesList().subscribe(data => {
    //   for(let i in data){
    //     data[i].currencyMask = createNumberMask({
    //       prefix: data.currencySymbol,
    //       suffix: '',
    //       includeThousandsSeparator: true,
    //       allowDecimal: true,
    //       decimalLimit: 4,
    //       requireDecimal: true,
    //       allowNegative: false,
    //       allowLeadingZeroes: false
    //     });
    //     this.currencyMap[data[i].currencyCode] = data[i];
    //   }
    // });
  }

  unmask(amount) {
    return amount.replace(/\D+/g, '');
  }

  onSubmit() {

    this.submitted = true;

    if (this.quotationForm.invalid) {
        return;
    }

    let currentDate = new Date();
    let dateString = currentDate.getFullYear() + "-" +(currentDate.getMonth() + 1) + "-" + currentDate.getDate();
    this.exchangeRequest.exchangeDate = dateString;
    this.exchangeRequest.amount = parseFloat(this.quotationForm.controls.amount.value.slice(2));
    this.exchangeService.getExchangePrice(this.exchangeRequest)
      .subscribe(data => {
        this.calculateQuotation(data);
      }, error => {
        console.log(error)
      });
  }

  calculateQuotation(exchangeResponse : ExchangeResponse){
    this.exchangeResponse.quotationAmount = exchangeResponse.exchangeRate * this.exchangeRequest.amount;
  }

}
