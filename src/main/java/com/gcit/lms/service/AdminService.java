package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService {
	
	///////////////////////////////////////BooK//////////////////////////////////////////////////////////////////////
	
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public Book getBookById(int id) throws SQLException {
		Connection conn = null;
		Book b = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			b = bdao.getBookById(id);
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
		return b;
	}
	
	public List<Book> getBookList() throws SQLException{
		Connection conn = null;
		List<Book> b = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			b = bdao.readAllBooks();
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
		return b;
	}
	
	public void addBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			int i =bdao.addBookWithID(book);
			book.setBookId(i);
			System.out.println(book);
			System.out.println(bdao.getBookById(i));
			bdao.updateBookRelations(book);
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
	
	public void editBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.editBook(book);
			bdao.updateBookRelations(book);
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
	
	public void deleteGenre(Book book, Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteGenre(genre, book);
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
	
	public void deleteAuthor(Book book, Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteAuthor(author, book);
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
	
	public void deletePublisher(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deletePublisher(book);
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
	
	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
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
	
	/////////////////////////////////////////////AUTHOR//////////////////////////////////////////////////////////
	
	public Author getAuthorById(int id) throws SQLException {
		Connection conn = null;
		Author a = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			a = adao.readAuthorByPK(id);
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
	
	public List<Author> getAuthorList() throws SQLException{
		Connection conn = null;
		List<Author> a = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			a = adao.readAllAuthors();
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
	
	public void addAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			author.setAuthorId(adao.addAuthorWithID(author));
			adao.updateAuthorBookRelations(author);
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
	
	public void editAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.editAuthor(author);
			adao.updateAuthorBookRelations(author);
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
	
	
	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO bdao = new AuthorDAO(conn);
			bdao.deleteAuthor(author);
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
	/////////////////////////////////Genres///////////////////////////////////////////////////////////
	
	public Genre getGenreById(int id) throws SQLException {
		Connection conn = null;
		Genre a = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			a = adao.readGenreByPK(id);
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
	
	public List<Genre> getGenreList() throws SQLException{
		Connection conn = null;
		List<Genre> a = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			a = adao.readAllGenres();
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
	
	public void addGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			adao.addGenre(genre);
			adao.updateGenreBookRelations(genre);
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
	
	public void editGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			adao.editGenre(genre);
			adao.updateGenreBookRelations(genre);
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
	
	
	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO bdao = new GenreDAO(conn);
			bdao.deleteGenre(genre);
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
	
	//////////////////////////////////////Publisher///////////////////////////////////////////////////////
	
	public Publisher getPublisherById(int id) throws SQLException {
		Connection conn = null;
		Publisher a = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			a = adao.readPublisherByPK(id);
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
	
	public List<Publisher> getPublisherList() throws SQLException{
		Connection conn = null;
		List<Publisher> a = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			a = adao.readAllpublishers();
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
	
	public void addPublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			adao.addPublisher(publisher);
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
	
	public void editPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			adao.editPublisher(publisher);
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
	
	
	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.deletePublisher(publisher);
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
	
	////////////////////////////////////////LibBranch////////////////////////////////////////////
	
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
	
	//////////////////////////////////////////////Borrower////////////////////////////////////////////////////
	
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

	
	////////////////////////////////////////BookLoan/////////////////////////////////////////////////////////
	
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
	
}
