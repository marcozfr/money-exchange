package com.example.exchange.repositories.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exchange.model.ExchangeRate;
import com.example.exchange.repositories.ExchangeRepository;

@Repository
public class JpaExchangeRepository implements ExchangeRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.example.exchange.repositories.ExchangeRepository#getExchangeRate(java.util.Date, java.lang.String, java.lang.String)
	 */
	public ExchangeRate getExchangeRate(Date exchangeDate, String originCurrency, String destinationCurrency) {
		StringBuilder sb = new StringBuilder();
		sb.append("select r from ExchangeRate r ");
		sb.append(" where trunc(r.exchangeDate) = trunc(:exchangeDate) ");
		sb.append(" and r.originCurrency.currencyCode = :occ ");
		sb.append(" and r.destinationCurrency.currencyCode = :dcc ");
		sb.append(" order by r.exchangeDate desc ");
		Query rate = entityManager.createQuery(sb.toString());
		rate.setParameter("exchangeDate", exchangeDate);
		rate.setParameter("occ", originCurrency);
		rate.setParameter("dcc", destinationCurrency);
		rate.setMaxResults(1);
		try {
			return (ExchangeRate) rate.getSingleResult();
		} catch (NoResultException  e) {
			return null;
		}
	}

}
