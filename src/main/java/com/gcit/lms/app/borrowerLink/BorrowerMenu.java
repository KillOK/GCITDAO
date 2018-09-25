package com.gcit.lms.app.borrowerLink;

import java.util.Scanner;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.BorrowerService;

public class BorrowerMenu {
	
	Scanner sc=null;
	BorrowerService service=null;
	Borrower borrower=null;
	
	BorrowerMenu(Scanner sc, BorrowerService service, Borrower borrower){
		if(sc!=null)this.sc = sc;
		else sc=new Scanner(System.in);
		if(service!=null)this.service=service;
		else service = new BorrowerService();
		this.borrower=borrower;
	}
	
	private String menuOptions = new String(
			"1) Check out a book\n" + 
			"2) Return a Book\n" + 
			"3) Quit to Previous ");
	
	public void start() {
		getMenu();
	};
	
	public void getMenu(){
		startmenu();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				switch (i) {
				case 1:
					new BorrowerCheckOut(sc, service,  borrower).start();
					getMenu();
					break;
				case 2:
					new BorrowerBookReturn(sc,  service,  borrower).start();
					getMenu();
					break;
				case 3:
					break;
				default:
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
		System.out.println(menuOptions);
		
	}
	
}



















