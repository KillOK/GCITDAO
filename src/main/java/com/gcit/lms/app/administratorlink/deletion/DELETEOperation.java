package com.gcit.lms.app.administratorlink.deletion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.app.administratorlink.CRUD;
import com.gcit.lms.app.administratorlink.CRUD.classes;
import static com.gcit.lms.app.util.UsefullMethods.getIntFromInput;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

public class DELETEOperation {
	
	Scanner sc = null;
	AdminService service; 
	
	public DELETEOperation(Scanner sc, AdminService service) {
		this.sc=sc;
		this.service = new AdminService();
	}
	
	public void start() {
		primaryMenu();
	}
	
	void primaryMenu(){
		System.out.println("Select Table to delete from");
		
		for(int i =0; i<classes.values().length;i++) {
			System.out.println((i+1)+") "+classes.values()[i]);
		}
		
		int i = sc.nextInt();
		System.out.println("\n*************************************************************************************************************");
		int j = 0 ;
		switch (i) {
		case 1:
			try {
				List<Author> bl= new ArrayList();
				service.getAuthorList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}else{
					System.out.println("Operation failed... please enter correct integer value next time");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				List<Book> bl= new ArrayList();
				service.getBookList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) System.out.println((k+1)+") "+bl.get(k));
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) CRUD.delete(bl.get(j-1));
				else System.out.println("Operation failed... please enter correct integer value next time");
			} catch (SQLException e) {e.printStackTrace();}
			break;
		case 3:
			try {
				List<Genre> bl= new ArrayList();
				service.getGenreList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}
				else System.out.println("Operation failed... please correct your input");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				List<Publisher> bl= new ArrayList();
				service.getPublisherList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}else{
					System.out.println("Operation failed... please enter correct integer value next time");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			try {
				List<LibBranch> bl= new ArrayList();
				service.getLibBranchList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}else{
					System.out.println("Operation failed... please enter correct integer value next time");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				List<Borrower> bl= new ArrayList();
				service.getBorrowerList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}else{
					System.out.println("Operation failed... please enter correct integer value next time");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 7:
			try {
				List<BookLoan> bl= new ArrayList();
				service.getBookLoanList().forEach(bl::add);
				for(int k=0; k<bl.size();k++) {
					System.out.println((k+1)+") "+bl.get(k));
				}
				System.out.println("Enter number like: 1) of object, which you want to delete");
				j=getIntFromInput();
				if(j>0&&j<bl.size()-1) {
					CRUD.delete(bl.get(j-1));
				}else{
					System.out.println("Operation failed... please enter correct integer value next time");
				}
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
