package com.gcit.lms.app.administratorlink.creation;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.app.administratorlink.CRUD.classes;
import static  com.gcit.lms.app.util.UsefullMethods.getLineFromInput;
import static  com.gcit.lms.app.util.UsefullMethods.getIntFromInput;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.ConnectionUtil;

public class CREATEOperation {
	
	Scanner sc = null;
	AdminService service; 
	
	public CREATEOperation(Scanner sc, AdminService service) {
		this.sc= new Scanner(System.in);
		this.service = new AdminService();
	}
	
	public void start() {
		primaryMenu();
	}
	
	void primaryMenu(){
		System.out.println("Select Table to insert data");
		
		for(int i =0; i<classes.values().length;i++) {
			System.out.println((i+1)+") "+classes.values()[i]);
		}
		
		int i = sc.nextInt();
		System.out.println("\n*************************************************************************************************************");
		switch (i) {
		case 1:
				List<Book> bookList = new ArrayList<>();
				Author author = new Author();
				System.out.println("Enter new author name");
				author.setAuthorName(getLineFromInput());
				System.out.println(author);
				try {
					service.addAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			break;
		case 2:
				List<Author> bl= new ArrayList();
				Book book = new Book();
				System.out.println("Enter new book Title");
				book.setTitle(getLineFromInput());
				try {
					service.addBook(book);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		case 3:
				List<Book> booklist = new ArrayList();
				Genre genre = new Genre();
				System.out.println("Enter new Genre");
				genre.setGenreName(getLineFromInput());
				try {
					service.addGenre(genre);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		case 4:
				List<Book> publisherbooklist = new ArrayList();
				Publisher publisher = new Publisher();
				System.out.println("Enter new Publisher name\nPrint N/A if you dont want to enter the field");
				publisher.setPubName(getLineFromInput());
				System.out.println("Enter new Publisher address\nPrint N/A if you dont want to enter the field");
				publisher.setPubAddress(getLineFromInput());
				System.out.println("Enter new Publisher phone\nPrint N/A if you dont want to enter the field");
				publisher.setPubPhone(getLineFromInput());
				try {
					service.addPublisher(publisher);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
			
		case 5:
				LibBranch libBranch = new LibBranch();
				List<BookCopy> bookcopielist = new ArrayList();
				List<BookLoan> loans = new ArrayList();
				System.out.println("Enter new Brunch name\nPrint N/A if you dont want to enter the field");
				libBranch.setBranchName(getLineFromInput());
				System.out.println("Enter new Brunch address\nPrint N/A if you dont want to enter the field");
				libBranch.setBranchAdress(getLineFromInput());
				try {
					service.addLibBranch(libBranch);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		case 6:
				List<BookLoan> borrowerloanlist = new ArrayList();
				Borrower borrower = new Borrower();
				System.out.println("Enter new Borrower name\nPrint N/A if you dont want to enter the field");
				borrower.setName(getLineFromInput());
				System.out.println("Enter new Borrower address\nPrint N/A if you dont want to enter the field");
				borrower.setAdress(getLineFromInput());
				System.out.println("Enter new Borrower phone\nPrint N/A if you dont want to enter the field");
				borrower.setPhone(getLineFromInput());
				try {
					service.addBorrower(borrower);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			break;
		case 7:
			System.out.println("You cant create loans, login as user to execute this operation");
		
		System.out.println("*************************************************************************************************************\n");
		
		}
	
	}
}
	





















