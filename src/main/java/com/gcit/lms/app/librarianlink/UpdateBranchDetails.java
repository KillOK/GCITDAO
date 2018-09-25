package com.gcit.lms.app.librarianlink;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.service.LibrarianService;

public class UpdateBranchDetails {
	Scanner sc;
	LibrarianService service;
	LibBranch branch ;
	private String notification = new String("You have chosen to update the Branch with Branch Id:"+/*branch.getBranchId()+*/" and Branch Name: "/*+branch.getBranchName()*/+". Enter ‘quit’ at any prompt to cancel operation.");
	private String menuOption1 = new String("Please enter new branch name or enter N/A for no change:");
	private String menuOption2 = new String("Please enter new branch address or enter N/A for no change:");
	String newName="";
	String newAddress="";
	
	
	UpdateBranchDetails(Scanner sc, LibrarianService service, LibBranch branch){
		this.sc = sc;
		this.service=service;
		this.branch = branch;
		notification = new String("You have chosen to update the Branch with Branch Id:"+branch.getBranchId()+" and Branch Name: "+branch.getBranchName()+". Enter ‘quit’ at any prompt to cancel operation.");
		menuOption1 = new String("Please enter new branch name or enter N/A for no change:");
		menuOption2 = new String("Please enter new branch address or enter N/A for no change:");
		
	}
	
	public void start() {
		getMenu();
	};
	
	public void getMenu(){
		Scanner sc = new Scanner(System.in);
		startmenu(notification);
		System.out.println(menuOption1);
		newName=sc.nextLine();
		if(!newName.equals("quite")) {
			System.out.println(menuOption2);
			newAddress=sc.nextLine();
			if(!newName.equals("quite")) {
				System.out.println("New name: "+newName+" new address: "+newAddress+"\n"
						+ "to update enter 1...");
				if(sc.hasNextInt())
					if(sc.nextInt()==1) {
						branch.setBranchName(newName.toUpperCase().equals("N/A".toUpperCase())?branch.getBranchName():newName);
						branch.setBranchAdress(newAddress.toUpperCase().equals("N/A".toUpperCase())?branch.getBranchAdress():newAddress);
						try {
							service.editLibBranch(branch);
							System.out.println("successfully updated...");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
			}
		}
		System.out.println((newName.toUpperCase().equals("N/A".toUpperCase())?branch.getBranchName():newName)+" "+(newAddress.toUpperCase().equals("N/A".toUpperCase())?branch.getBranchAdress():newAddress));
	}
	
	

	
	public void startmenu(String message) {
		System.out.println(message);
		
	}
	
}