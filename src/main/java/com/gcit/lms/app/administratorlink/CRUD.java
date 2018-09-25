package com.gcit.lms.app.administratorlink;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.ConnectionUtil;

public class CRUD {
	
	public enum classes{
		AUTHOR,BOOK,GENRE,PUBLISHER,LIBRARY,BORROWER,LOAN
	}
	
	public enum typeOfOperration{
		CREATE,READE,UPDATE,DELETE
	}
	
	public static Connection conn;

	public static AuthorDAO adao = new AuthorDAO(conn);
	public static BookDAO bdao = new BookDAO(conn);
	public static GenreDAO gdao = new GenreDAO(conn);
	public static LibBranchDAO branchdao = new LibBranchDAO(conn);
	public static BorrowerDAO bordao = new BorrowerDAO(conn);
	public static BookLoanDAO loandao = new BookLoanDAO(conn);
	public static BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
	
	CRUD(){
		
	}
	
	public static AdminService adminService;

	static{
		adminService= new AdminService();
		try {
			conn = new ConnectionUtil().getConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////Create//////////////////////////////////////////

	public static void create(Book book) throws SQLException {
		adminService.addBook(book);
	}
	
	public static void create(Author a) throws SQLException {
		adminService.addAuthor(a);
	}
	
	public static void create(Genre a) throws SQLException {
		adminService.addGenre(a);
	}
	
	public static void create(Publisher a) throws SQLException {
		adminService.addPublisher(a);
	}
	
	public static void create(LibBranch a) throws SQLException {
		adminService.addLibBranch(a);
	}
	
	public static void create(Borrower a) throws SQLException {
		adminService.addBorrower(a);
	}
	
	///////////////////////////////////////////Update//////////////////////////////////////////////////
	
	public static void update(Book book) throws SQLException {
		adminService.editBook(book);
	}
	
	public static void update(Author a) throws SQLException {
		adminService.editAuthor(a);
	}
	
	public static void update(Genre a) throws SQLException {
		adminService.editGenre(a);
	}
	
	public static void update(Publisher a) throws SQLException {
		adminService.editPublisher(a);
	}
	
	public static void update(LibBranch a) throws SQLException {
		adminService.editLibBranch(a);
	}
	
	public static void update(Borrower a) throws SQLException {
		adminService.editBorrower(a);
	}
	
////////////////////////////////Delete/////////////////////////////////////////
	
	public static void delete(BookLoan bookLoan) throws SQLException {
		adminService.deleteBookLoan(bookLoan);
	}
	
	public static void delete(Author a) throws SQLException {
		adminService.deleteAuthor(a);
	}
	
	public static void delete(Genre a) throws SQLException {
		adminService.deleteGenre(a);
	}
	
	public static void delete(Publisher a) throws SQLException {
		adminService.deletePublisher(a);
	}
	
	public static void delete(LibBranch a) throws SQLException {
		adminService.deleteLibBranch(a);
	}
	
	public static void delete(Borrower a) throws SQLException {
		adminService.deleteBorrower(a);
	}
	public static void delete(Book a) throws SQLException {
		adminService.deleteBook(a);
	}
	
}













