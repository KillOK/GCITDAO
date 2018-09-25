package com.gcit.lms.app.librarianlink;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.LibrarianService;

public class LibrarianLink {
	Scanner sc = null;
	LibrarianService service; 
	LibBranch branch = null;
	List<LibBranch> branchlist = new ArrayList<>();
	
	private String menuOptions = new String(
			"1) Enter Branch you manage\n" + 
			"2) Quit to Previous ");
	
	public LibrarianLink(Scanner sc) {
		this.sc=sc;
		this.service = new LibrarianService();
		try {
			branchlist = service.getLibBranchList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void startLink() {
		firstLibMenu();
	}
	
	public void firstLibMenu(){
		startmenu();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				switch (i) {
				case 1:
					brunchInitialization();
					firstLibMenu();
					break;
				case 2:
					break;
				default:
					firstLibMenu();
					break;
				}
			}else if(sc.hasNext()){
				sc.next();
				firstLibMenu();
			}
			break;
		}
	}
	
	public void startmenu() {
		System.out.println(menuOptions);
		
	}
	
	public void branchPickMenu() {
		try {
			branchlist = service.getLibBranchList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Select Your Library Branch");
		for(int i=0;i<branchlist.size();i++) {
			System.out.println((i+1)+") "+branchlist.get(i).getBranchName()+" "+branchlist.get(i).getBranchAdress());
		}
		System.out.println((branchlist.size()+1)+") Quit to Previous");
	}
	
	public void brunchInitialization() {

		branchPickMenu();
		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				int i = sc.nextInt();
				if(i>branchlist.size()+1||i<1) {
					System.out.println("Enter Correct Branch Number");
					brunchInitialization();
					break;
				}else if(i==branchlist.size()+1){
					break;
				}
				else {
					branch = (branchlist.get(i-1));
					System.out.println("New Link should start here");
					System.out.println(branch);
					if(branch!=null) {
						new LibrarianMenu(sc, service , branch).start();
					}
					brunchInitialization();
					break;
				}
			}else if(sc.hasNext()){
				sc.next();
				brunchInitialization();
				break;
			}
			break;
		}
	
	}
	
	
}