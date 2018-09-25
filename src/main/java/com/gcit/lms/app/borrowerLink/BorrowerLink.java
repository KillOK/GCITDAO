package com.gcit.lms.app.borrowerLink;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.BorrowerService;

public class BorrowerLink {
	Scanner sc = null;
	BorrowerService service; 
	Borrower  borrower = null;
	int cardNumber = -1;
	
	public BorrowerLink(Scanner sc) {
		this.sc=sc;
		this.service = new BorrowerService();
	}
	
	public void startLink() {
		borrowerAutentification();
		System.out.println(borrower);
		if(borrower!=null)new BorrowerMenu(sc, service , borrower).start();
	}
	
	public void borrowerAutentification() {
		String s = "";
		System.out.println("Enter your card number");
		if(sc.hasNext()) {
			if (sc.hasNextInt()){
				cardNumber = sc.nextInt();
				borrower = getBorrowerByCardNumber(cardNumber);
			}else {
				s = sc.next();
				System.out.println("Your input: "+s);
			}
		}
		if(borrower==null) {
			if(!s.equals("exit")) {
				System.out.println("Enter your data correctly...");
				borrowerAutentification();
			}
		}
	}
	
	public Borrower getBorrowerByCardNumber(int cardNumber) {
		Borrower  borrower = null;
		try {
			borrower = service.getBorrowerById(cardNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}
	
	
}










