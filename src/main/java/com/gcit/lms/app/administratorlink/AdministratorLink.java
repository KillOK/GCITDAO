package com.gcit.lms.app.administratorlink;

import java.util.Scanner;


import static  com.gcit.lms.app.util.UsefullMethods.getLineFromInput;
import static  com.gcit.lms.app.util.UsefullMethods.getIntFromInput;
import com.gcit.lms.app.administratorlink.CRUD.typeOfOperration;
import com.gcit.lms.app.administratorlink.creation.CREATEOperation;
import com.gcit.lms.app.administratorlink.deletion.DELETEOperation;
import com.gcit.lms.app.administratorlink.reade.READEOperation;
import com.gcit.lms.app.administratorlink.update.UPDATEOperation;
import com.gcit.lms.service.AdminService;

public class AdministratorLink {
	
	Scanner sc = null;
	AdminService service; 
	
	public AdministratorLink(Scanner sc) {
		this.sc=sc;
		this.service = new AdminService();
	}
	
	public void startLink() {
		mainMenu();
	}
	
	void mainMenu(){
		
//		for (CRUD.classes crud : classes.values()) { 
//		    System.out.println(crud); 
//		}
		
		System.out.println("Enter operation, which you want to implement");
		
		for(int i =0; i<typeOfOperration.values().length;i++) {
			System.out.println((i+1)+") "+typeOfOperration.values()[i]);
		}
		
		int i = getIntFromInput();
		
		if(i>0&&i<typeOfOperration.values().length+1) {
			switch (i) {
			case 1: 
				new CREATEOperation(sc, service ).start();
				break;
			case 2: 
				new READEOperation(sc, service ).start();
				break;
			case 3: 
				new UPDATEOperation(sc, service ).start();
				break;
			case 4: 
				new DELETEOperation(sc, service ).start();
				break;
			default:
				break;
			}
		}
		  
	}

}
