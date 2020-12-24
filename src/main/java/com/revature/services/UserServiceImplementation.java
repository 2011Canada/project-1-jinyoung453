package com.revature.services;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;

import com.revature.models.Displayable;
import com.revature.models.User;
import com.revature.repositories.UserDao;

public class UserServiceImplementation implements UserService {
	
	private UserDao ud;
	
	public UserServiceImplementation(UserDao ud) {
		this.ud = ud;
	}
	
	public User login(String username, String password) {
			User u = ud.findUserByUsernameAndPassword(username, password);
			//Entertainment720Launcher.setCurrentUser(u);
			return u;	
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return ud.findAll();
	}
}