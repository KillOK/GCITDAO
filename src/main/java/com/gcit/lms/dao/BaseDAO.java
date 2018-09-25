package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseDAO<T> {
	
	public static Connection conn= null;
	
	public static Connection getConn() {
		return conn;
	}

	public BaseDAO(Connection connection){
		BaseDAO.conn = connection;
	}
	
	public void save(String sql, Object[] vals) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public Integer saveWithID(String sql, Object[] vals) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				System.out.println(o);
				System.out.println(sql);
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		Integer i = -1;
		if(rs.next())
			i = rs.getInt(1);
		return i;
	}

	public List<T> read(String sql, Object[] vals) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract public List<T> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	public List<T> readFirstLevel(String sql, Object[] vals) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(rs);
	}
	
	abstract public List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

}
