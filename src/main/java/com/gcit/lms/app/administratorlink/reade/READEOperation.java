package com.gcit.lms.app.administratorlink.reade;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.app.administratorlink.CRUD.classes;
import com.gcit.lms.app.administratorlink.CRUD.typeOfOperration;
import com.gcit.lms.service.AdminService;

public class READEOperation {
	
	Scanner sc = null;
	AdminService service; 
	
	public READEOperation(Scanner sc, AdminService service) {
		this.sc=sc;
		this.service = new AdminService();
	}
	
	public void start() {
		primaryMenu();
	}
	
	void primaryMenu(){
		System.out.println("Select Table to read");
		
		for(int i =0; i<classes.values().length;i++) {
			System.out.println((i+1)+") "+classes.values()[i]);
		}
		
		int i = sc.nextInt();
		System.out.println("\n*************************************************************************************************************");

		switch (i) {
		case 1:
			try {
				service.getAuthorList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				service.getBookList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				service.getGenreList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				service.getPublisherList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			try {
				service.getLibBranchList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				service.getBorrowerList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 7:
			try {
				service.getBookLoanList().forEach(System.out::println);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
		System.out.println("*************************************************************************************************************\n");
	}
	
}












