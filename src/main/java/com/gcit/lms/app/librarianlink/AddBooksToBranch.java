package com.gcit.lms.app.librarianlink;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.LibrarianService;

public class AddBooksToBranch {
Scanner sc=null;
LibrarianService service=null;
LibBranch branch = null;
List<Book> allBooks = new ArrayList<>();

AddBooksToBranch(Scanner sc, LibrarianService service, LibBranch branch){
	if(sc!=null)this.sc = sc;
	else sc=new Scanner(System.in);
	if(service!=null)this.service=service;
	else service = new LibrarianService();
	this.branch = branch;
	try {
		this.allBooks = service.getBookList();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

private String menuOptions = new String(
		"  Enter number of book, which you want to Update");
private String menuEnd = " Quit to previous";
private Book book;

public void start() {
	getMenu();
};

public void getMenu(){
	startmenu();
	
	while(sc.hasNext()) {
		if(sc.hasNextInt()) {
			int i = sc.nextInt();
			if(i>allBooks.size()+1||i<1) {
				System.out.println("Enter Correct Branch Number");
				getMenu();
				break;
			}else if(i==allBooks.size()+1){
				break;
			}
			else {
				book = (allBooks.get(i-1));
				updateBookCopy(book);
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
	System.out.println(menuOptions);
	for(int i=0;i<allBooks.size();i++) {
		System.out.print((i+1)+") "+allBooks.get(i).getTitle()+" by ");
		for(int j=0;j<allBooks.get(i).getAuthors().size()-1;j++) {
			System.out.print(allBooks.get(i).getAuthors().get(j)+", ");
		}
		int authorsNumber = allBooks.get(i).getAuthors().size();
		if(authorsNumber>0)System.out.println(allBooks.get(i).getAuthors().get(allBooks.get(i).getAuthors().size()-1));
		else System.out.println();
	}
	System.out.println((allBooks.size()+1)+") "+menuEnd);
}

public void updateBookCopy(Book b){
	
	int copiesNumb = 0;
	BookCopy copy = null;
	try {
		copy = service.getBookCopyById(b.getBookId(), branch.getBranchId());
		System.out.println(copy);
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	if(copy==null) {
		copiesNumb = 0;
		copy = new BookCopy(b,branch,copiesNumb);
		try {
			service.addBookCopy(copy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Existing number of copies: "+copiesNumb);
		System.out.println("Enter new number of copies:");
		copiesNumb=sc.nextInt();
		copy.setCopieNumbersInBranch(copiesNumb);
		try {
			service.editBookCopy(copy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(service.getBookCopyById(b.getBookId(),branch.getBranchId() ));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		copiesNumb = copy.getCopieNumbersInBranch();
		System.out.println("Existing number of copies: "+copiesNumb);
		System.out.println("Enter new number of copies:");
		copiesNumb=sc.nextInt();
		copy.setCopieNumbersInBranch(copiesNumb);
		try {
			service.editBookCopy(copy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(service.getBookCopyById(b.getBookId(),branch.getBranchId() ));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


}










