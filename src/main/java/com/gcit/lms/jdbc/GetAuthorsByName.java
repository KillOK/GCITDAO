package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetAuthorsByName {

	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public static String url = "jdbc:mysql://localhost/library?useSSL=false";

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			System.out.println("Enter Author Name to search for: ");
			String authorName = scan.nextLine();
			// Register your driver
			Class.forName(driver).newInstance();

			// Create a connection
			Connection conn = DriverManager.getConnection(url, user, password);

			// Create Statement
			PreparedStatement pstmt = conn.prepareStatement("Select * from tbl_author where authorName = ?");
			pstmt.setString(1, authorName);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("Author ID: " + rs.getInt("authorId"));
				System.out.println("Author Name: " + rs.getString("authorName"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(scan!=null)scan.close();
		}

	}

}
