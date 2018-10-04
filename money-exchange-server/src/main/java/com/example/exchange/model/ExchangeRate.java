package com.example.exchange.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EXCHANGE_RATE")
public class ExchangeRate {
    
    @Id @GeneratedValue
    @Column(name="EXCHANGE_ID")
    private Long exchangeId;
    
    @Column(name="EXCHANGE_RATE")
    private BigDecimal exchangeRate;
    
    @Column(name="EXCHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORIGIN_CURRENCY_CODE")
    private Currency originCurrency;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DEST_CURRENCY_CODE")
    private Currency destinationCurrency;

	public Long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
    
    
    
}