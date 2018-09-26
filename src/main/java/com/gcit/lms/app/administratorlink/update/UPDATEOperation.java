package com.gcit.lms.app.administratorlink.update;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static  com.gcit.lms.app.util.UsefullMethods.getLineFromInput;
import static  com.gcit.lms.app.util.UsefullMethods.getIntFromInput;
import com.gcit.lms.app.administratorlink.CRUD.classes;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

public class UPDATEOperation {
	
	Scanner sc = null;
	AdminService service; 
	
	public UPDATEOperation(Scanner sc, AdminService service) {
		this.sc=new Scanner(System.in);
		this.service = new AdminService();
	}
	
	public void start() {
		primaryMenu();
	}
	
	void primaryMenu(){
		System.out.println("Select Table to update data");
		
		for(int i =0; i<classes.values().length;i++) {
			System.out.println((i+1)+") "+classes.values()[i]);
		}
		
		int i = getIntFromInput();
		System.out.println("\n*************************************************************************************************************");
		///////////////There also should be branches to populate and set Lists/////////////////////////////////
		int j = 0 ;
		String buffer="";
		switch (i) {
		case 1:
				List<Book> bookList = new ArrayList<>();
				Author author = new Author();
				List<Author> authorList = new ArrayList<>();
				try {
					authorList = service.getAuthorList();
					for(int aid=0; aid<authorList.size(); aid++) {
						System.out.println((aid+1)+") "+authorList.get(aid));
					}
					System.out.println("Enter id of author, which data you want to modify");
					int pk=getIntFromInput();
					author = service.getAuthorById(pk);
					System.out.println("Enter new author name");
					author.setAuthorName(getLineFromInput());
					service.editAuthor(author);
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
				
			break;
		case 2:
				List<Author> bl= new ArrayList();
				Book book = new Book();
				List<Book> bookListGeneral = new ArrayList<>();
				try {
					bookList = service.getBookList();
					for(int aid=0; aid<bookListGeneral.size(); aid++) {
						System.out.println((aid+1)+") "+bookListGeneral.get(aid));
					}
					System.out.println("Enter id of book, which data you want to modify:");
					int bookpk=0;
					bookpk=getIntFromInput();
					book = service.getBookById(bookpk);
					System.out.println("Enter book title:");
					book.setTitle(getLineFromInput());
					service.editBook(book);
				} catch (SQLException | NullPointerException e1) {
					e1.printStackTrace();
				}
			break;
		case 3:
				List<Book> booklist = new ArrayList();
				List<Genre> genrelist = new ArrayList();
				Genre genre;
				try {
					genrelist = service.getGenreList();
					j=0;
					for(Genre g:genrelist) {
						j++;
						System.out.println(j+") id: "+ g.getGenreId()+" name: "+ g.getGenreName());
					}
					System.out.println("Enter id of genre, which you want to update");
					genre = service.getGenreById(getIntFromInput());
					System.out.println("Enter new genre name");
					genre.setGenreName(getLineFromInput());
					System.out.println(genre);
					service.editGenre(genre);
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
			break;
		case 4:
				List<Book> publisherbooklist = new ArrayList();
				Publisher publisher;
				try {
					j=0;
					for(Publisher p:service.getPublisherList()) {
						j++;
						System.out.println(j+") id: "+ p.getPublisherId()+" name: "+ p.getPubName());
					}
					System.out.println("Enter id of publisher, which data you want to edit");
					publisher = service.getPublisherById(getIntFromInput());
					System.out.println("Enter new Publisher name\nPrint N/A if you dont want to enter the field");
					buffer=getLineFromInput();
					if(!buffer.toUpperCase().equals("N/A"))publisher.setPubName(buffer);
					System.out.println("Enter new Publisher address\nPrint N/A if you dont want to enter the field");
					buffer=getLineFromInput();
					if(!buffer.toUpperCase().equals("N/A"))publisher.setPubAddress(buffer);
					System.out.println("Enter new Publisher phone\nPrint N/A if you dont want to enter the field");
					buffer=getLineFromInput();
					if(!buffer.toUpperCase().equals("N/A"))publisher.setPubPhone(buffer);
					System.out.println(publisher);
					service.editPublisher(publisher);
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
			break;
			
		case 5:
			String brunchName  = "";
			String brunchAddress  = "";
			LibBranch libBranch = new LibBranch();
			List<BookCopy> bookcopielist = new ArrayList();
			List<BookLoan> loans = new ArrayList();			
			try {
			j=0;
			for(LibBranch branch:service.getLibBranchList()) {
				j++;
				System.out.println(j+") id: "+ branch.getBranchId()+" name: "+ branch.getBranchName()+" address: "+branch.getBranchAdress());
			}
			System.out.println("Enter id of publisher, which data you want to edit");
			libBranch = service.getLibBranchById(getIntFromInput());
			System.out.println("Enter new Brunch name\nPrint N/A if you dont want to enter the field");
			brunchName = getLineFromInput();
			if(!brunchName.toUpperCase().equals("N/A"))libBranch.setBranchName(brunchName);
			System.out.println("Enter new Brunch address\nPrint N/A if you dont want to enter the field");
			brunchName = getLineFromInput();
			if(!brunchName.toUpperCase().equals("N/A"))libBranch.setBranchAdress(brunchAddress);
				service.editLibBranch(libBranch);
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			break;
	
		case 6:
			try {
				j=0;
				for(Borrower borrower:service.getBorrowerList()) {
					j++;
					System.out.println(j+") CardNo: "+ borrower.getCardNo()+" name: "+ borrower.getName()+" address: "+borrower.getAdress()+" phone: "+borrower.getPhone());
				}
				System.out.println("Enter card Number of borrower, which datd you want to update: \n");
				Borrower borrower = service.getBorrowerById(getIntFromInput());
				System.out.println("Enter new Borrower name\nPrint N/A if you dont want to enter the field");
				buffer=getLineFromInput();
				if(!buffer.toUpperCase().equals("N/A"))borrower.setName(buffer);
				System.out.println("Enter new Borrower address\nPrint N/A if you dont want to enter the field");
				buffer=getLineFromInput();
				if(!buffer.toUpperCase().equals("N/A"))borrower.setAdress(buffer);
				System.out.println("Enter new Borrower phone\nPrint N/A if you dont want to enter the field");
				buffer=getLineFromInput();
				if(!buffer.toUpperCase().equals("N/A"))borrower.setPhone(buffer);
				service.editBorrower(borrower);
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			break;
		case 7:
			BookLoan bookLoan = null;
			List<BookLoan> loanList= new ArrayList<>();
			System.out.println("1) To override due date press 1\n2) Go to main menu");
			j=getIntFromInput();
			if(j==1) {
				try {
					loanList=service.getBookLoanList();
					j=0;
					for(BookLoan loan:loanList) {
						j++;
						System.out.println(j+") BookID: "+loan.getBook().getBookId()+" BorrowerID: "+loan.getBorrower().getCardNo()+" Borrower Name: "+loan.getBorrower().getName()+" Library Branch: id "+loan.getBranch().getBranchId()+" "+loan.getBranch().getBranchName()+" Due Date: "+loan.getDueDate());                                      
					}
					System.out.println("Enter number of loan, which you want to override");
					bookLoan = loanList.get(getIntFromInput()-1);
					bookLoan.setDueDate(new Date(new java.util.Date().getTime()+(7*24*60*60*1000)));
					service.editBookLoan(bookLoan);
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
			}else {				
				break;
			}
		}
		System.out.println("*************************************************************************************************************\n");
	
	}
}
	



















