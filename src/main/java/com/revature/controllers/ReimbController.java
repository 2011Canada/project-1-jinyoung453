package com.revature.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimburse;
import com.revature.models.User;
import com.revature.repositories.ReimbPostgresDao;
import com.revature.services.ReimbService;
import com.revature.services.ReimbServiceImplementation;

public class ReimbController {

	private ReimbService rs = new ReimbServiceImplementation(new ReimbPostgresDao());
	
	private ObjectMapper om = new ObjectMapper();

	public void findAllReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {

		List<Reimburse> allReimburse = rs.getAllReimburse();
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allReimburse));
		
	}
	
	public void findAllReimbursement(HttpServletRequest req, HttpServletResponse res, int resolverId) throws IOException {

		List<Reimburse> allReimburse = rs.getAllReimburse(resolverId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allReimburse));
		
	}
	
	//Status
	//complexId = userId + statusId (1:Pending,	2:Approved, 	3:Rejected)
	public void findReimbursementByStatus(HttpServletRequest req, HttpServletResponse res, int complexId) throws IOException {

		List<Reimburse> reimburse = rs.getReimburseByStatus(complexId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(reimburse));
		
	}
	
	//complexId = userId + statusId
	public void findReimbursementByUserIdStatus(HttpServletRequest req, HttpServletResponse res, int complexId) throws IOException {

		List<Reimburse> reimburse = rs.getReimburseByAuthorStatus(complexId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(reimburse));
		
	}
	
	public void findReimbursementByUserId(HttpServletRequest req, HttpServletResponse res, int userId) throws IOException {
		List<Reimburse> reimburse = rs.getReimburseByAuthor(userId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(reimburse));
		
	}
	
	public void approval(HttpServletRequest req, HttpServletResponse res, int statusId) throws IOException {
		ObjectMapper om = new ObjectMapper();
		Reimburse rb = om.readValue(req.getInputStream(), Reimburse.class);
		rs.setReimburseStatus(rb.getId(), rb.getResolver(), statusId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(rb));
	}
	
	public void createReimb(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try{
			System.out.println("createReimb");
			ObjectMapper om = new ObjectMapper();
			Reimburse reimb = om.readValue(req.getInputStream(), Reimburse.class);
//			Part filePart;
//			String fileName = "";
//			InputStream fileContent = null;
//			try {
//				filePart = req.getPart("receipt");
//				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//				fileContent = filePart.getInputStream();
			
			rs.create(reimb);
			res.setStatus(200);
			System.out.println("REIMBURSEMENT IS CREATED: " + reimb.toString());
		}catch(IOException e){
		}
	}
	
	
}