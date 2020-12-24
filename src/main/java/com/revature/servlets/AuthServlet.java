package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Credentials;
import com.revature.models.User;

public class AuthServlet extends HttpServlet{

	//this would be a service but for this demo we will skip making an entire new class
		static List<User> allUsers = new ArrayList<User>();
		
		static {
			allUsers.add(new User(1, "AB", "a", "alec", "Batson", "ba@aa.aa", 1));
			allUsers.add(new User(2, "AC", "a", "alec", "Catson", "ca@aa.aa", 1));
			allUsers.add(new User(3, "AD", "a", "alec", "Datson", "da@aa.aa", 2));
		}
		
		ObjectMapper om = new ObjectMapper();
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//object mapper takes in JSON and turns to specified object, or vice versa
			Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
			
			System.out.println(cred);
			boolean found = false;
			for(User u : allUsers) {
				if(cred.getUsername().equals(u.getUsername()) && cred.getPassword().equals(u.getPassword())) {
					resp.setStatus(200);
					resp.getWriter().write(om.writeValueAsString(u));
					resp.setHeader("Content-Type", "application/json");
					found = true;
					break;
				}
			}
			
			if(!found) {
				resp.setStatus(401);
				resp.getWriter().write("Username or Password Incorrect");
			}
		}
}
