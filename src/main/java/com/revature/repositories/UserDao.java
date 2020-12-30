package com.revature.repositories;

import java.util.List;
import java.util.Map;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.FinanceManager;
import com.revature.models.User;

public interface UserDao {

	public User findUserByUsernameAndPassword(String userId, String password) throws UserNotFoundException, InternalErrorException;
	public List<User> findAll();
	public void createUser(User user);
	public List<FinanceManager> findAllFinanceManagers();
}