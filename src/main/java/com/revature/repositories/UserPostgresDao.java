package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserPostgresDao implements UserDao {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from ers_users where ERS_USERNAME = ? and ERS_PASSWORD = ? ;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			getUser.setString(1, username);
			getUser.setString(2, password);

			ResultSet res = getUser.executeQuery();
			if(res.next()) {
				User u = new User();
				u.setUserId(res.getInt("ERS_USERS_ID"));
				u.setFirstName(res.getString("USER_FIRST_NAME"));
				u.setLastName(res.getString("USER_LAST_NAME"));
				u.setUsername(res.getString("ERS_USERNAME"));
				u.setPassword(res.getString("ERS_PASSWORD"));
				u.setEmail(res.getString("USER_EMAIL"));
				u.setRoleId(res.getInt("USER_ROLE_ID"));
				return u;
			}else {
				throw new UserNotFoundException();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}

	public List<User> findAll() {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from ers_users;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			
			ResultSet res = getUser.executeQuery();
			List<User> allUsers = new ArrayList<User>();
			while(res.next()) {
				User u = new User();
				u.setUserId(res.getInt("ERS_USERS_ID"));
				u.setFirstName(res.getString("USER_FIRST_NAME"));
				u.setLastName(res.getString("USER_LAST_NAME"));
				u.setUsername(res.getString("ERS_USERNAME"));
				u.setPassword(res.getString("ERS_PASSWORD"));
				u.setEmail(res.getString("USER_EMAIL"));
				u.setRoleId(res.getInt("USER_ROLE_ID"));
				allUsers.add(u);
			}
			return allUsers;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	
	
	
	
	

}