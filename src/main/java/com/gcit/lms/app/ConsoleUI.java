package com.gcit.lms.app;

import java.util.Scanner;

import com.gcit.lms.app.administratorlink.AdministratorLink;
import com.gcit.lms.app.borrowerLink.BorrowerLink;
import com.gcit.lms.app.librarianlink.LibrarianLink;

public class ConsoleUI {
	
	Scanner sc = new Scanner(System.in);
	
	public void start(){
		new Menu(sc).getMenu();		
	}
	
}

class Menu{
	Scanner sc;
	

	public Menu(Scanner sc) {
		this.sc = sc;
	}
	
	public void getMenu(){
		System.out.println("Hello!\n");
		startmenu();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				switch (i) {
				case 1:
					getLibrarianlink();
					break;
				case 2:
					getAdministratorlink();
					break;
				case 3:
					getBorrowerlink();
					break;
				default:
					System.out.println("You should enter code of operation listed before...");
					break;
				}
			}else if(sc.next().equals("exit")) {
				break;
			}else {
				System.out.println("You should enter code of operation listed before...");
				getMenu();
				break;
			}
			startmenu();			
		}
	}
	
	public void startmenu() {
		System.out.println("Select user category:\n"
				+ "1) Librarian\n"
				+ "2) Administrator\n"
				+ "3) Borrower");
		
	}
	
	public void getLibrarianlink() {
		System.out.println("Librarian link;");
		new LibrarianLink(sc).startLink();
		
	}
	public void getAdministratorlink() {
		System.out.println("Administrator link;");
		new AdministratorLink(sc).startLink();
	}
	public void getBorrowerlink() {
		System.out.println("Borrower link;");
		new BorrowerLink(sc).startLink();
	}
}






