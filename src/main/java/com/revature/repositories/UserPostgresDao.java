package com.revature.repositories;

import java.io.CharArrayReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.FinanceManager;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserPostgresDao implements UserDao {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public User findUserByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from ers_users where ERS_USERNAME = ? and pgp_sym_decrypt(ERS_PASSWORD::bytea, 'AES_KEY') = ? ;";
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
				//u.setPassword(res.getString("ERS_PASSWORD"));
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
				//u.setPassword(res.getString("ERS_PASSWORD"));
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
	
	public void createUser(User user) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql = "insert into ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) "
						+ "values(?, PGP_SYM_ENCRYPT(?, 'AES_KEY'), ?, ?, ?, ?);";
			
			PreparedStatement insertUser = conn.prepareStatement(sql);
			
			insertUser.setString(1, user.getUsername());
			insertUser.setString(2, user.getPassword());
			insertUser.setString(3, user.getFirstName());
			insertUser.setString(4, user.getLastName());
			insertUser.setString(5, user.getEmail());
			insertUser.setInt(6, user.getRoleId());
			
			insertUser.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
 	}
	
	public List<FinanceManager> findAllFinanceManagers(){
		Connection conn = cf.getConnection();
		try {
			String sql = "select ers_users_id as userId, user_first_name || '  ' || user_last_name as userName from ERS_USERS "
					+ "where user_role_id = 1;";
			PreparedStatement getFM = conn.prepareStatement(sql);
			ResultSet res = getFM.executeQuery();
			List<FinanceManager> allUsers = new ArrayList<FinanceManager>();
			while(res.next()) {
				FinanceManager fm = new FinanceManager();
				fm.setUserId(res.getInt("userId"));
				fm.setUserName(res.getString("userName"));
				allUsers.add(fm);
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