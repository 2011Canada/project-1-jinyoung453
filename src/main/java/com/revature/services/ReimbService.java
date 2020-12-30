package com.revature.services;

import java.io.InputStream;
import java.util.List;

import com.revature.models.Reimburse;
import com.revature.models.User;


public interface ReimbService {

	public List<Reimburse> getAllReimburse(int resolverId);
	public List<Reimburse> getReimburseByStatus(int complexId);
	public List<Reimburse> getReimburseByAuthor(int author);
	public List<Reimburse> getReimburseByAuthorStatus(int complexId);
	public void setReimburseStatus(int reimbId, int resolverId, int statusId);
	public void create(Reimburse reimb);
	//public void create(Reimburse reimb, String fileName, InputStream fileContent);
}