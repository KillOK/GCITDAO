package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String user = "root";
	public String password = "root";
	public String url = "jdbc:mysql://localhost/library?useSSL=false";
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}
