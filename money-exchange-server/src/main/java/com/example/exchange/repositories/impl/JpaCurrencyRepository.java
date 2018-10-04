package com.example.exchange.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exchange.model.Currency;
import com.example.exchange.repositories.CurrencyRepository;

@Repository
public class JpaCurrencyRepository implements CurrencyRepository {
	
	@Autowired
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.example.exchange.repositories.CurrencyRepository#getCurrencies()
	 */
	@Override
	public List<Currency> getCurrencies() {
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Currency c");
		TypedQuery<Currency> rate = entityManager.createQuery(sb.toString(),Currency.class);
		return rate.getResultList();
	}

}
