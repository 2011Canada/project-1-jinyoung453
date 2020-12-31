package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.FinanceManager;
import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.repositories.UserPostgresDao;
import com.revature.services.UserService;
import com.revature.services.UserServiceImplementation;

public class UserController {

	private UserService us = new UserServiceImplementation(new UserPostgresDao());
	
	private ObjectMapper om = new ObjectMapper();
	

	public void findAllUsers(HttpServletRequest req, HttpServletResponse res) throws IOException {

//		YES!!!! I made it inactive!!! Let's see,  >> worked!!!!
//		HttpSession sess = req.getSession();
//		
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals("Admin")) {
//			throw new UnauthorizedException();
//		}
		List<User> allusers = us.getAllUsers();
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allusers));
		
	}
	
	public void createAccount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try{
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(req.getInputStream(), User.class);
			us.create(user);
			res.setStatus(200);
			System.out.println("USER IS CREATED: " + user.toString());
		}catch(IOException e){
		}
	}
	

	public void findApprover(HttpServletRequest req, HttpServletResponse res, int departmentId) throws IOException {

		List<FinanceManager> approver = us.getApprover(departmentId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(approver));
		
	}
	
}