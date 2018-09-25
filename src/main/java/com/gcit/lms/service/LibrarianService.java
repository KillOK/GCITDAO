package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.LibBranch;

public class LibrarianService {
	
	ConnectionUtil connUtil = new ConnectionUtil();
	
	////////////////////////////////////Branch/////////////////////////////////////////////
	
	public LibBranch getLibBranchById(int id) throws SQLException {
		Connection conn = null;
		LibBranch a = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO adao = new LibBranchDAO(conn);
			a = adao.readLibBranchByPK(id);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
	public List<LibBranch> getBranchListByName(String branchName) throws SQLException{
		Connection conn = null;
		List<LibBranch> a = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO adao = new LibBranchDAO(conn);
			a = adao.readAllLibBranchsByName(branchName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
	
	public List<LibBranch> getLibBranchList() throws SQLException{
		Connection conn = null;
		List<LibBranch> a = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO adao = new LibBranchDAO(conn);
			a = adao.readAllLibBranches();
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
	public void addLibBranch(LibBranch libBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO adao = new LibBranchDAO(conn);
			adao.addLibBranch(libBranch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void editLibBranch(LibBranch libBranch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO adao = new LibBranchDAO(conn);
			adao.editLibBranch(libBranch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	
	public void deleteLibBranch(LibBranch libBranch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			LibBranchDAO bdao = new LibBranchDAO(conn);
			bdao.deleteLibBranch(libBranch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	////////////////////////////////////////////BookCopies////////////////////////////////////////////////////////
	
	
	public BookCopy getBookCopyById(int bookId, int branchId) throws SQLException {
		Connection conn = null;
		BookCopy a = null;
		try {
			conn = connUtil.getConnection();
			BookCopyDAO adao = new BookCopyDAO(conn);
			a = adao.readBookCopyByPK( bookId,  branchId);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
	public List<BookCopy> getBookCopyList() throws SQLException{
		Connection conn = null;
		List<BookCopy> a = null;
		try {
			conn = connUtil.getConnection();
			BookCopyDAO adao = new BookCopyDAO(conn);
			a = adao.readAllBookCopies();
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
	public void addBookCopy(BookCopy bookCopy) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopyDAO adao = new BookCopyDAO(conn);
			adao.addBookCopy(bookCopy);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void editBookCopy(BookCopy bookCopy) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopyDAO adao = new BookCopyDAO(conn);
			adao.editBookCopy(bookCopy);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	
	public void deleteBookCopy(BookCopy bookCopy) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopyDAO bdao = new BookCopyDAO(conn);
			bdao.deleteBookCopy(bookCopy);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Book> getBookList() throws SQLException{
		Connection conn = null;
		List<Book> a = null;
		try {
			conn = connUtil.getConnection();
			BookDAO adao = new BookDAO(conn);
			a = adao.readAllBooks();
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return a;
	}
	
}
