package com.stackroute.userservice.services;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {
	boolean saveUser(User user) throws UserAlreadyExistsException;

	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
