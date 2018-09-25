package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EditAuthor {

	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public static String url = "jdbc:mysql://localhost/library?useSSL=false";

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			System.out.println("Enter Author Name to add: ");
			String authorName = scan.nextLine();
			// Register your driver
			Class.forName(driver).newInstance();

			// Create a connection
			Connection conn = DriverManager.getConnection(url, user, password);

			// Create Statement
			PreparedStatement pstmt = conn.prepareStatement("insert into tbl_author (authorName) values(?)");
			pstmt.setString(1, authorName);
			
			pstmt.executeUpdate();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(scan!=null)scan.close();
		}

	}

}
