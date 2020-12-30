package com.revature.repositories;

import java.io.InputStream;
import java.util.List;

import com.revature.models.Reimburse;
import com.revature.models.User;

public interface ReimbDao {

	public List<Reimburse> findAllReimburse(int id);
	public List<Reimburse> findReimburseByStatus(int id);
	public List<Reimburse> findReimburseByUserId(int id);
	public List<Reimburse> findReimburseByUserIdStatus(int id);
	public void updateStatus(int reimbId, int resolverId, int statusId);
	public void createReimburse(Reimburse reimb);
	//public void createReimburse(Reimburse reimb, String fileName, InputStream fileContent);
}