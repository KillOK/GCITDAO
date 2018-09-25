package com.gcit.lms.app.borrowerLink;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.BorrowerService;

public class BorrowerCheckOut {
	Scanner sc=null;
	BorrowerService service=null;
	Borrower borrower=null;
	List<LibBranch> branchList = new ArrayList<>();
	List<BookCopy> onSiteBooks = new ArrayList<>();
	
	BorrowerCheckOut(Scanner sc, BorrowerService service, Borrower borrower){
		this.sc = sc;
		this.service=service;
		this.borrower=borrower;
		try {
			branchList = service.getLibBranchList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String branchMenuOptions = new String(
			"Pick the branch you want to check out from:");
	private String onSiteBookMenuOptions = new String(
			"Pick the book you want to check out:");
	
	public void start() {
		getMenu();
	};
	
	public void getMenu(){
		startmenu();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				if(i>branchList.size()+1||i<1) {
					System.out.println("Enter Correct Branch Number");
					getMenu();
					break;
				}else if(i==branchList.size()+1){
					break;
				}
				else {
					chooseTheBookCopie(branchList.get(i-1));
					getMenu();
					break;
				}
			}else if(sc.hasNext()){
				sc.next();
				getMenu();
				break;
			}
			break;
		}
	}
	
	public void startmenu() {
		if(branchList.isEmpty())
		try {
			branchList = service.getLibBranchList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(branchMenuOptions);
		for(int i=0;i<branchList.size();i++) {
			System.out.println(i+1+") "+branchList.get(i).getBranchName()+" "+branchList.get(i).getBranchAdress());
		}
		System.out.println(branchList.size()+1+") Quite to previous");
	}
	
	
	
	public void chooseTheBookCopieMenu(LibBranch branch){
		try {
			onSiteBooks = new ArrayList<>();
			service.getOnSiteBookCopyList(branch).stream().filter(p -> p.getCopieNumbersInBranch()>0).forEach(onSiteBooks::add);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(onSiteBookMenuOptions);
		for(int i=0;i<onSiteBooks.size();i++) {
			System.out.println((i+1)+") "+onSiteBooks.get(i).getBook().getTitle()+" "+onSiteBooks.get(i).getBook().getAuthors());
		}
		System.out.println(onSiteBooks.size()+1+") Quite to previous");
		
	}
	
	public void chooseTheBookCopie(LibBranch branch) {
		chooseTheBookCopieMenu(branch);
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				if(i>onSiteBooks.size()+1||i<1) {
					System.out.println("Enter Correct Book Number");
					chooseTheBookCopie(branch);
					break;
				}else if(i==onSiteBooks.size()+1){
					break;
				}
				else {
					formLoanPapers(onSiteBooks.get(i-1), branch);
					chooseTheBookCopie(branch);
					break;
				}
			}else if(sc.hasNext()){
				sc.next();
				chooseTheBookCopie(branch);
				break;
			}
			break;
		}
	}
	
	public void formLoanPapers(BookCopy book, LibBranch branch) {
		service.formBookLoan(book,branch,borrower);
	}
	
}













