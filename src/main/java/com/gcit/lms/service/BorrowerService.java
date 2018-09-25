package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;

public class BorrowerService {
	
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public Borrower getBorrowerById(int id) throws SQLException {
		Connection conn = null;
		Borrower a = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			a = adao.readBorrowerByPK(id);
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
		
		public List<Borrower> getBorrowerList() throws SQLException{
			Connection conn = null;
			List<Borrower> a = null;
			try {
				conn = connUtil.getConnection();
				BorrowerDAO adao = new BorrowerDAO(conn);
				a = adao.readAllBorrowers();
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
		
		public void addBorrower(Borrower borrower) throws SQLException{
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BorrowerDAO adao = new BorrowerDAO(conn);
				adao.addBorrower(borrower);
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
		
		public void editBorrower(Borrower borrower) throws SQLException {
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BorrowerDAO adao = new BorrowerDAO(conn);
				adao.editBorrower(borrower);
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
		
		
		public void deleteBorrower(Borrower borrower) throws SQLException {
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.deleteBorrower(borrower);
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
		
/////////////////////////////////////////Branch/////////////////////////////////////////////////////////////////////
	
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
		
		/////////////////////////////////////BookCopies////////////////////////////////////////////////////
		
		
		public BookCopy getBookCopyById(int bookId, int branchId) throws SQLException {
			Connection conn = null;
			BookCopy a = null;
			try {
				conn = connUtil.getConnection();
				BookCopyDAO adao = new BookCopyDAO(conn);
				a = adao.readBookCopyByPK(bookId, branchId);
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
		
		public List<BookCopy> getOnSiteBookCopyList(LibBranch libBranch) throws SQLException{
			Connection conn = null;
			List<BookCopy> a = null;
			try {
				conn = connUtil.getConnection();
				BookCopyDAO adao = new BookCopyDAO(conn);
				a = adao.readAllBookCopiesInBranch(libBranch);
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
		
		
		
		
		//////////////////////////////////////BookLoan////////////////////////////////////////////////////////
		
		public BookLoan getBookLoanById(int bookId, int branchId, int cardNo, Date dueDate) throws SQLException {
			Connection conn = null;
			BookLoan a = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				a = adao.readBookLoanByPK(bookId, branchId, cardNo, dueDate);
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
		
		public List<BookLoan> getBookLoanList() throws SQLException{
			Connection conn = null;
			List<BookLoan> a = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				a = adao.readAllbookLoans();
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
		
		public List<BookLoan> getBookLoanListByBorrower(Borrower borrower) throws SQLException{
			Connection conn = null;
			List<BookLoan> a = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				a = adao.readAllbookLoansByBorrower(borrower.getCardNo());
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
		
		public void addBookLoan(BookLoan loan) throws SQLException{
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				adao.addBookLoan(loan);
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
		
		public void editBookLoan(BookLoan loan) throws SQLException {
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				adao.editBookLoanDate(loan);
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
		
		
		public void deleteBookLoan(BookLoan loan) throws SQLException {
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO bdao = new BookLoanDAO(conn);
				bdao.deleteBookLoan(loan);
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
		
		public void formBookLoan(BookCopy book, LibBranch branch, Borrower borrower) {
			Date dateOut = new Date(new java.util.Date().getTime());
			Date dueDate = new Date(new java.util.Date().getTime()+(7*24*60*60*1000));
			Connection conn = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO loandao = new BookLoanDAO(conn);
				BookCopyDAO copydao = new BookCopyDAO(conn);
				BookLoan bookLoan = new BookLoan(book.getBook(),branch, borrower, dueDate, dateOut);
				book.setCopieNumbersInBranch(book.getCopieNumbersInBranch()-1);
				copydao.editBookCopy(book);
				loandao.addBookLoan(bookLoan);
				conn.commit();
				System.out.println("CheckOut successful");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Something went wrong, try again...");
				e.printStackTrace();
			}
		}
		
			public void formCheckIn(BookLoan loan) {
			Date dateIn = new Date(new java.util.Date().getTime());
			Connection conn = null;
			BookCopy copy = null;
			try {
				conn = connUtil.getConnection();
				BookLoanDAO adao = new BookLoanDAO(conn);
				adao.editBookLoanDate(loan);
				copy =getBookCopyById(loan.getBook().getBookId(), loan.getBranch().getBranchId());
				copy.setCopieNumbersInBranch(copy.getCopieNumbersInBranch()+1);
				editBookCopy(copy);
				conn.commit();
				System.out.println("CheckIn successful");
			} catch (SQLException e) {
				System.out.println("Something went wrong, try again...");
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
}
