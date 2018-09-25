package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAllAuthors {

	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public static String url = "jdbc:mysql://localhost:3306/library?useSSL=false&useUnicode=true";

	public static void main(String[] args) {
		try {
			// Register your driver
			Class.forName(driver).newInstance();

			// Create a connection
			Connection conn = DriverManager.getConnection(url, user, password);

			// Create Statement
			Statement stmt = conn.createStatement();

			String sql = "Select * from tbl_author";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("Author ID: " + rs.getInt("authorId"));
				System.out.println("Author Name: " + rs.getString("authorName"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
