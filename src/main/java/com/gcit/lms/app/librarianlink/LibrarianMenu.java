package com.gcit.lms.app.librarianlink;

import java.util.Scanner;

import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.LibrarianService;

public class LibrarianMenu {
	Scanner sc=null;
	LibrarianService service=null;
	LibBranch branch = null;
	
	LibrarianMenu(Scanner sc, LibrarianService service, LibBranch branch){
		this.sc = sc;
		this.service=service;
		this.branch = branch;
	}
	
	private String menuOptions = new String(
			"1) Update the details of the Library\n" + 
			"2) Add copies of Book to the Branch\n"
			+ "3) Quit to previous");
	
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
					new UpdateBranchDetails(sc,  service, branch).start();
					getMenu();
					break;
				case 2:
					new AddBooksToBranch(sc, service,branch).start();
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
			}
			break;
		}
	}
	
	public void startmenu() {
		System.out.println(menuOptions);
		
	}
	
}