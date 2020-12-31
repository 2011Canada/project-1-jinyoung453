package com.revature.repositories;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class ReimbPostgresDao implements ReimbDao {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public List<Reimburse> findAllReimburse() { 
		Connection conn = cf.getConnection();
		try {
			String sql = "select er.*,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_author = eu.ers_users_id) as REIMB_AUTHOR_NAME,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_resolver = eu.ers_users_id) as REIMB_RESOLVER_NAME,"
					+ "(select ert.reimb_type from ers_reimbursement_type ert where er.reimb_type_id = ert.reimb_type_id) as REIMB_TYPE,"
					+ "(select ers.reimb_status from ers_reimbursement_status ers where er.reimb_status_id = ers.reimb_status_id) as REIMB_STATUS"
					+ " from ers_reimbursement er order by er.reimb_id desc;";
			
			PreparedStatement getReimburse = conn.prepareStatement(sql);
			
			ResultSet res = getReimburse.executeQuery();
			List<Reimburse> allReimburse = new ArrayList<Reimburse>();
			while(res.next()) {
				Reimburse r = new Reimburse();
				r.setId(res.getInt("REIMB_ID"));
				r.setAmount(res.getDouble("REIMB_AMOUNT"));
				r.setSubmit(res.getString("REIMB_SUBMITTED"));
				r.setApprove(res.getString("REIMB_RESOLVED"));
				r.setDesc(res.getString("REIMB_DESCRIPTION"));
				r.setReceipt(res.getString("REIMB_RECEIPT"));
				r.setAuthor(res.getInt("REIMB_AUTHOR"));
				r.setResolver(res.getInt("REIMB_RESOLVER"));
				r.setStatus(res.getInt("REIMB_STATUS_ID"));
				r.setType(res.getInt("REIMB_TYPE_ID"));
				r.setAuthorName(res.getString("REIMB_AUTHOR_NAME"));
				if(res.getString("REIMB_RESOLVER_NAME")==null || res.getString("REIMB_RESOLVER_NAME").equals("")) {
					r.setResolverName("-");
				}else {
					r.setResolverName(res.getString("REIMB_RESOLVER_NAME"));
				}
				r.setStatusName(res.getString("REIMB_STATUS"));
				r.setTypeName(res.getString("REIMB_TYPE"));
				allReimburse.add(r);
			}
			return allReimburse;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	public List<Reimburse> findAllReimburse(int id) { //resolverId
		//System.out.println("findAllReimburse_id: " + id);
		Connection conn = cf.getConnection();
		try {
			String sql = "select er.*,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_author = eu.ers_users_id) as REIMB_AUTHOR_NAME,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_resolver = eu.ers_users_id) as REIMB_RESOLVER_NAME,"
					+ "(select ert.reimb_type from ers_reimbursement_type ert where er.reimb_type_id = ert.reimb_type_id) as REIMB_TYPE,"
					+ "(select ers.reimb_status from ers_reimbursement_status ers where er.reimb_status_id = ers.reimb_status_id) as REIMB_STATUS"
					+ " from ers_reimbursement er where er.reimb_resolver = " + id + " order by er.reimb_id desc;";
			
			PreparedStatement getReimburse = conn.prepareStatement(sql);
			
			ResultSet res = getReimburse.executeQuery();
			List<Reimburse> allReimburse = new ArrayList<Reimburse>();
			while(res.next()) {
				Reimburse r = new Reimburse();
				r.setId(res.getInt("REIMB_ID"));
				r.setAmount(res.getDouble("REIMB_AMOUNT"));
				r.setSubmit(res.getString("REIMB_SUBMITTED"));
				r.setApprove(res.getString("REIMB_RESOLVED"));
				r.setDesc(res.getString("REIMB_DESCRIPTION"));
				r.setReceipt(res.getString("REIMB_RECEIPT"));
				r.setAuthor(res.getInt("REIMB_AUTHOR"));
				r.setResolver(res.getInt("REIMB_RESOLVER"));
				r.setStatus(res.getInt("REIMB_STATUS_ID"));
				r.setType(res.getInt("REIMB_TYPE_ID"));
				r.setAuthorName(res.getString("REIMB_AUTHOR_NAME"));
				if(res.getString("REIMB_RESOLVER_NAME")==null || res.getString("REIMB_RESOLVER_NAME").equals("")) {
					r.setResolverName("-");
				}else {
					r.setResolverName(res.getString("REIMB_RESOLVER_NAME"));
				}
				r.setStatusName(res.getString("REIMB_STATUS"));
				r.setTypeName(res.getString("REIMB_TYPE"));
				allReimburse.add(r);
			}
			return allReimburse;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	////complexId = userId + statusId(1:Pending,	 2:Approved, 	3:Rejected)
	public List<Reimburse> findReimburseByStatus(int complexId) {
		int statusId = complexId % 10;
		int userId = complexId / 10;
		System.out.println("userId : " + userId);
		Connection conn = cf.getConnection();
		try {
			String sql = "select er.*,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_author = eu.ers_users_id) as REIMB_AUTHOR_NAME,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_resolver = eu.ers_users_id) as REIMB_RESOLVER_NAME,"
					+ "(select ert.reimb_type from ers_reimbursement_type ert where er.reimb_type_id = ert.reimb_type_id) as REIMB_TYPE,"
					+ "(select ers.reimb_status from ers_reimbursement_status ers where er.reimb_status_id = ers.reimb_status_id) as REIMB_STATUS"
					+ " from ers_reimbursement er";
			//view All Reimbursement
			if(userId == 0) {
				sql += " where er.reimb_status_id=" + statusId + " order by er.reimb_id desc;";
			}
			//view Assigned Reimbursement
			else {	
				sql += " where er.reimb_status_id=" + statusId + " and er.reimb_resolver=" + userId + " order by er.reimb_id desc;";
			}
			
					
			
			PreparedStatement getReimburse = conn.prepareStatement(sql);
			
			ResultSet res = getReimburse.executeQuery();
			List<Reimburse> reimburseList = new ArrayList<Reimburse>();
			while(res.next()) {
				Reimburse r = new Reimburse();
				r.setId(res.getInt("REIMB_ID"));
				r.setAmount(res.getDouble("REIMB_AMOUNT"));
				r.setSubmit(res.getString("REIMB_SUBMITTED"));
				r.setApprove(res.getString("REIMB_RESOLVED"));
				r.setDesc(res.getString("REIMB_DESCRIPTION"));
				r.setReceipt(res.getString("REIMB_RECEIPT"));
				r.setAuthor(res.getInt("REIMB_AUTHOR"));
				r.setResolver(res.getInt("REIMB_RESOLVER"));
				r.setStatus(res.getInt("REIMB_STATUS_ID"));
				r.setType(res.getInt("REIMB_TYPE_ID"));
				r.setAuthorName(res.getString("REIMB_AUTHOR_NAME"));
				if(res.getString("REIMB_RESOLVER_NAME")==null || res.getString("REIMB_RESOLVER_NAME").equals("")) {
					r.setResolverName("-");
				}else {
					r.setResolverName(res.getString("REIMB_RESOLVER_NAME"));
				}
				r.setStatusName(res.getString("REIMB_STATUS"));
				r.setTypeName(res.getString("REIMB_TYPE"));
				reimburseList.add(r);
			}
			return reimburseList;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}

////complexId = userId + statusId(1:Pending,	 2:Approved, 	3:Rejected)
	public List<Reimburse> findReimburseByUserIdStatus(int complexId) {
		int statusId = complexId % 10;
		int userId = complexId / 10;
		Connection conn = cf.getConnection();
		
		try {
			String sql = "select er.*,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_author = eu.ers_users_id) as REIMB_AUTHOR_NAME,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_resolver = eu.ers_users_id) as REIMB_RESOLVER_NAME,"
					+ "(select ert.reimb_type from ers_reimbursement_type ert where er.reimb_type_id = ert.reimb_type_id) as REIMB_TYPE,"
					+ "(select ers.reimb_status from ers_reimbursement_status ers where er.reimb_status_id = ers.reimb_status_id) as REIMB_STATUS"
					+ " from ers_reimbursement er"
					+ " where er.reimb_status_id=" + statusId + " and er.reimb_author=" + userId + " order by er.reimb_id desc;";
			
			PreparedStatement getReimburse = conn.prepareStatement(sql);
			
			ResultSet res = getReimburse.executeQuery();
			List<Reimburse> allReimburse = new ArrayList<Reimburse>();
			while(res.next()) {
				Reimburse r = new Reimburse();
				r.setId(res.getInt("REIMB_ID"));
				r.setAmount(res.getDouble("REIMB_AMOUNT"));
				r.setSubmit(res.getString("REIMB_SUBMITTED"));
				r.setApprove(res.getString("REIMB_RESOLVED"));
				r.setDesc(res.getString("REIMB_DESCRIPTION"));
				r.setReceipt(res.getString("REIMB_RECEIPT"));
				r.setAuthor(res.getInt("REIMB_AUTHOR"));
				r.setResolver(res.getInt("REIMB_RESOLVER"));
				r.setStatus(res.getInt("REIMB_STATUS_ID"));
				r.setType(res.getInt("REIMB_TYPE_ID"));
				r.setAuthorName(res.getString("REIMB_AUTHOR_NAME"));
				if(res.getString("REIMB_RESOLVER_NAME")==null || res.getString("REIMB_RESOLVER_NAME").equals("")) {
					r.setResolverName("-");
				}else {
					r.setResolverName(res.getString("REIMB_RESOLVER_NAME"));
				}
				r.setStatusName(res.getString("REIMB_STATUS"));
				r.setTypeName(res.getString("REIMB_TYPE"));
				allReimburse.add(r);
			}
			return allReimburse;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
	}
	public List<Reimburse> findReimburseByUserId(int userId){
		Connection conn = cf.getConnection();
		try {
			String sql = "select er.*,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_author = eu.ers_users_id) as REIMB_AUTHOR_NAME,"
					+ "(select eu.user_first_name || '  ' || eu.user_last_name from ers_users eu where er.reimb_resolver = eu.ers_users_id) as REIMB_RESOLVER_NAME,"
					+ "(select ert.reimb_type from ers_reimbursement_type ert where er.reimb_type_id = ert.reimb_type_id) as REIMB_TYPE,"
					+ "(select ers.reimb_status from ers_reimbursement_status ers where er.reimb_status_id = ers.reimb_status_id) as REIMB_STATUS"
					+ " from ers_reimbursement er"
					+ " where er.reimb_author=" + userId + " order by er.reimb_id desc;";
			
			PreparedStatement getReimburse = conn.prepareStatement(sql);
			
			ResultSet res = getReimburse.executeQuery();
			List<Reimburse> allReimburse = new ArrayList<Reimburse>();
			while(res.next()) {
				Reimburse r = new Reimburse();
				r.setId(res.getInt("REIMB_ID"));
				r.setAmount(res.getDouble("REIMB_AMOUNT"));
				r.setSubmit(res.getString("REIMB_SUBMITTED"));
				r.setApprove(res.getString("REIMB_RESOLVED"));
				r.setDesc(res.getString("REIMB_DESCRIPTION"));
				r.setReceipt(res.getString("REIMB_RECEIPT"));
				r.setAuthor(res.getInt("REIMB_AUTHOR"));
				r.setResolver(res.getInt("REIMB_RESOLVER"));
				r.setStatus(res.getInt("REIMB_STATUS_ID"));
				r.setType(res.getInt("REIMB_TYPE_ID"));
				r.setAuthorName(res.getString("REIMB_AUTHOR_NAME"));
				if(res.getString("REIMB_RESOLVER_NAME")==null || res.getString("REIMB_RESOLVER_NAME").equals("")) {
					r.setResolverName("-");
				}else {
					r.setResolverName(res.getString("REIMB_RESOLVER_NAME"));
				}
				r.setStatusName(res.getString("REIMB_STATUS"));
				r.setTypeName(res.getString("REIMB_TYPE"));
				allReimburse.add(r);
			}
			return allReimburse;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	public void updateStatus(int reimbId, int resolverId, int statusId) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			Calendar cal = Calendar.getInstance();
			cal.setTime( new java.util.Date() );
			
			String sql = "update ers_reimbursement " +
					 	 "set reimb_resolver = ?, reimb_status_id = ? , reimb_resolved = ? " + 
					 	 "where reimb_id = ? ;";
			
			PreparedStatement updateReimb = conn.prepareStatement(sql);
			
			updateReimb.setInt(1, resolverId);
			updateReimb.setInt(2, statusId);
			updateReimb.setTimestamp( 3, new java.sql.Timestamp( cal.getTime().getTime()));
			updateReimb.setInt(4, reimbId);

			
			updateReimb.executeUpdate();
			
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
	
	public void createReimburse(Reimburse reimb) {
		Connection conn = cf.getConnection();
		Calendar cal = Calendar.getInstance();
		cal.setTime( new java.util.Date() );
		try {
			conn.setAutoCommit(false);
			
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement insertUser = conn.prepareStatement(sql);
			
			insertUser.setDouble(1, reimb.getAmount());
			insertUser.setTimestamp(2, new java.sql.Timestamp(cal.getTime().getTime()));
			insertUser.setString(3, reimb.getDesc());
			insertUser.setString(4, reimb.getReceipt());
			insertUser.setInt(5, reimb.getAuthor());
			insertUser.setInt(6, reimb.getResolver());
			insertUser.setInt(7, 1);
			insertUser.setInt(8, reimb.getType());
			//insertUser.setString(9, fileName);
			
			System.out.println("insertUser: " + insertUser.toString());
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
}