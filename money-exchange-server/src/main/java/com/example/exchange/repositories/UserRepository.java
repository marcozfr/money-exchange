package com.example.exchange.repositories;

import com.example.exchange.model.User;

public interface UserRepository {
	
	public User findUserByDetails(String username);

}
