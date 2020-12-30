package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimburse;

public class ReimDaoDebugger {

	public static void main(String[] args) {

		ReimbDao rd = new ReimbPostgresDao();
		//Reimburse r = new Reimburse(22, new char[]{'1'} ,"NEW", "Kim", 1000.00, 'I', "NEW");
		
		/*
		List<Reimburse> rList = rd.findAllReimburse();
		
		for(Reimburse r : rList) {
			System.out.println(r.toString());
		}
		*/
		
		List<Reimburse> rList = rd.findReimburseByStatus(1);
		
		for(Reimburse r : rList) {
			System.out.println(r.toString());
		}
		

	}

}
