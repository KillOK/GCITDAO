package com.gcit.lms.app.borrowerLink;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static  com.gcit.lms.app.util.UsefullMethods.getLineFromInput;
import static  com.gcit.lms.app.util.UsefullMethods.getIntFromInput;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.BorrowerService;

public class BorrowerBookReturn {

	Scanner sc=null;
	BorrowerService service=null;
	Borrower borrower=null;
	List<LibBranch> branchList = new ArrayList<>();
	List<BookCopy> onSiteBooks = new ArrayList<>();
	List<BookLoan> borrowersBookLoans = new ArrayList<>();
	
	BorrowerBookReturn(Scanner sc, BorrowerService service, Borrower borrower){
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
			"Pick the branch you want to return book to:");
	private String loanMenuOptions = new String(
			"Pick the book you want to return:");
	
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
					chooseTheBookLoan(branchList.get(i-1));
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
		System.out.println(loanMenuOptions);
		try {
			borrowersBookLoans = new ArrayList<>();
			service.getBookLoanListByBorrower(borrower).stream().filter(p -> p.getBranch().getBranchId()==branch.getBranchId()).filter(p -> p.getDateIn()==null).forEach(borrowersBookLoans::add);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i=0;i<borrowersBookLoans.size();i++) {
			System.out.println((i+1)+") "+borrowersBookLoans.get(i).getBook().getTitle()+" "+borrowersBookLoans.get(i).getBook().getAuthors());
		}
		System.out.println(borrowersBookLoans.size()+1+") Quite to previous");
		
	}
	
	public void chooseTheBookLoan(LibBranch branch) {
		chooseTheBookCopieMenu(branch);
		while(true) {
				int i = getIntFromInput();
				if(i>borrowersBookLoans.size()+1||i<1) {
					System.out.println("Enter Correct Book ID");
					chooseTheBookLoan(branch);
					break;
				}else if(i==borrowersBookLoans.size()+1){
					break;
				} else {
					formLoanPapers(borrowersBookLoans.get(i-1));
//					chooseTheBookLoan(branch);
					break;
				}
			}
	}
	
	public void formLoanPapers(BookLoan loan) {
		try {
			service.formCheckIn(loan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}











