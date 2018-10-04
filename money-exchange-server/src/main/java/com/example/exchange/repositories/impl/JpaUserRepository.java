package com.example.exchange.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exchange.model.User;
import com.example.exchange.repositories.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {
	
	private static Logger logger = LoggerFactory.getLogger(JpaUserRepository.class);
	
	@Autowired
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.example.exchange.repositories.ExchangeRepository#getExchangeRate(java.util.Date, java.lang.String, java.lang.String)
	 */
	public User findUserByDetails(String username) {
		StringBuilder sb = new StringBuilder();
		sb.append("select u from User u ");
		sb.append(" where u.username = :username ");
		Query rate = entityManager.createQuery(sb.toString());
		rate.setParameter("username", username);
		User user = null;
		try {
			user = (User) rate.getSingleResult();
		} catch (NoResultException e) {
			logger.error("No matches found for user. Returning null");
		}
		return user;
		
	}
	
}
