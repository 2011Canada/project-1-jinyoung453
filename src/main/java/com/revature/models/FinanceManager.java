package com.revature.models;

public class FinanceManager {
	int userId;
	String userName;
	
	public FinanceManager(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public FinanceManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "FinanceManager [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
}
