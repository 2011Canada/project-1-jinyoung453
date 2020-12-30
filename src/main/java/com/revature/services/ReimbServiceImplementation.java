package com.revature.services;

import java.io.InputStream;
import java.util.List;

import com.revature.models.Reimburse;
import com.revature.repositories.ReimbDao;

public class ReimbServiceImplementation implements ReimbService {
	
	private ReimbDao rd;
	
	public ReimbServiceImplementation(ReimbDao rd) {
		this.rd = rd;
	}
	
	public List<Reimburse> getAllReimburse(int resolverId) {
		return rd.findAllReimburse(resolverId);
	}
	
	public List<Reimburse> getReimburseByStatus(int complexId){
		return rd.findReimburseByStatus(complexId);
	}
	
	public List<Reimburse> getReimburseByAuthorStatus(int complexId){
		return rd.findReimburseByUserIdStatus(complexId);
	}
	
	public List<Reimburse> getReimburseByAuthor(int author){
		return rd.findReimburseByUserId(author);
	}
	
	public void setReimburseStatus(int reimbId, int resolverId, int statusId) {
		rd.updateStatus(reimbId, resolverId, statusId);
	}
	public void create(Reimburse reimb) {
		rd.createReimburse(reimb);
	}
//	public void create(Reimburse reimb, String fileName, InputStream fileContent) {
//		rd.createReimburse(reimb, fileName, fileContent);
//	}
}