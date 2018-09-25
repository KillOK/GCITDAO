package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddAuthor {

	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public static String url = "jdbc:mysql://localhost/library?useSSL=false";

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			System.out.println("Enter Author Name to edit: ");
			String authorName = scan.nextLine();
			System.out.println("Enter Author ID to Edit: ");
			Integer authorId = scan.nextInt();
			// Register your driver
			Class.forName(driver).newInstance();

			// Create a connection
			Connection conn = DriverManager.getConnection(url, user, password);

			// Create Statement
			PreparedStatement pstmt = conn.prepareStatement("update tbl_author set authorName = ? where authorId = ?");
			pstmt.setString(1, authorName);
			pstmt.setInt(2, authorId);
			
			pstmt.executeUpdate();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(scan!=null)scan.close();
		}

	}

}
