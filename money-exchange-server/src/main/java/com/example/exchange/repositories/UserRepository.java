package com.example.exchange.repositories;

import com.example.exchange.model.User;

/**
 * @author marcof
 *
 */
public interface UserRepository {
	
	/**
	 * Finds a user based on a given number of parameters
	 * @param username
	 * @return The User object
	 */
	public User findUserByDetails(String username);

}
