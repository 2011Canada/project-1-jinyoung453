package com.revature.services;

import java.util.List;
import java.util.Map;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;

import com.revature.models.Displayable;
import com.revature.models.FinanceManager;
import com.revature.models.User;
import com.revature.repositories.UserDao;

public class UserServiceImplementation implements UserService {
	
	private UserDao ud;
	
	public UserServiceImplementation(UserDao ud) {
		this.ud = ud;
	}
	
	public User login(String username, String password) {
		return ud.findUserByUsernameAndPassword(username, password);
	}

	public List<User> getAllUsers() {
		return ud.findAll();
	}
	
	public void create(User user) {
		ud.createUser(user);
	}
	
	public List<FinanceManager> getAllApprovers(){
		return ud.findAllFinanceManagers();
	}
}