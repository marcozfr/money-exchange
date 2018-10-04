import { Component, OnInit } from '@angular/core';
import { ExchangeService } from '../../services/exchange/exchange.service';
import { Observable } from 'rxjs';
import { ExchangeRequest } from '../../model/exchange/exchangeRequest';
import { ExchangeResponse } from '../../model/exchange/exchangeResponse';
import createNumberMask from 'text-mask-addons/dist/createNumberMask';

@Component({
  selector: 'app-quotation',
  templateUrl: './quotation.component.html',
  styleUrls: ['./quotation.component.scss']
})
export class QuotationComponent implements OnInit {

  private defaultOriginCurrency = 'USD';
  private defaultDestinationCurrency = 'EUR';
  private exchangeRequest : ExchangeRequest = new ExchangeRequest();
  private exchangeResponse : ExchangeResponse = new ExchangeResponse();
  private currencyMask;

  constructor(private exchangeService : ExchangeService) {   }

  ngOnInit() {
    this.exchangeRequest.origin = this.defaultOriginCurrency;
    this.exchangeRequest.destination = this.defaultDestinationCurrency;
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
  }

  unmask(amount) {
    return amount.replace(/\D+/g, '');
  }

  getExchangeRate() {
    let currentDate = new Date();
    let dateString = currentDate.getFullYear() + "-" +(currentDate.getMonth() + 1) + "-" + currentDate.getDate();
    this.exchangeRequest.exchangeDate = dateString;
    this.exchangeRequest.amount = parseFloat(this.exchangeRequest.amountStr.slice(2));
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
