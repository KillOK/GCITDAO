package com.gcit.lms.app.util;

import java.util.Scanner;

public class UsefullMethods {
	public static Scanner sc = new Scanner(System.in);
	
	public static String getLineFromInput() {
		String s = "";
		while(sc.hasNext()) {
			if(sc.hasNextLine()){
				s=sc.nextLine();
				if((!s.equals(""))&&(s!=null)) {
					break;					
				}
			}else {
				sc.next();
			}
		}
		return s;
	}
	
	public static int getIntFromInput() {
		int s = 0;
		while(sc.hasNext()) {
			if(sc.hasNextInt()){
				s = sc.nextInt();
				break;
			}else {
				System.out.println("You should enter numeric value...");
				sc.nextLine();
				break;
			}
		}
		return s;
	}
}
