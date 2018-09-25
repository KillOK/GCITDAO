package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.LibBranch;

public class BookCopyDAO extends BaseDAO<BookCopy> {

	public BookCopyDAO(Connection connection) {
		super(connection);
	}

	public void addBookCopy(BookCopy bookCopy)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values(?,?,?)", new Object[] { bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId(), bookCopy.getCopieNumbersInBranch() });
	}

	public void editBookCopy(BookCopy bookCopy)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId=?", new Object[]{ bookCopy.getCopieNumbersInBranch(),  bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId() });
	}

	public void deleteBookCopy(BookCopy bookCopy)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book_copies where bookId = ?", new Object[]{bookCopy.getBook().getBookId()});
	}

	public List<BookCopy> readAllBookCopies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_copies", null);
	}
	
	public List<BookCopy> readAllBookCopiesInBranch(LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_copies where branchId = ? ", new Object[]{libBranch.getBranchId()});
	}
	
	public List<BookCopy> readAllbookCopiesByName(Book book, LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_copies where bookId = ? and branchId = ? ", new Object[]{book.getBookId(), libBranch.getBranchId()});
	}
	
	public BookCopy readBookCopyByPK(Integer bookId, Integer branchId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookCopy> bookCopies = read("Select * from tbl_book_copies where bookId = ? and branchId = ?", new Object[]{bookId, branchId});
		if(!bookCopies.isEmpty()){
			return bookCopies.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<BookCopy> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		LibBranchDAO branchdao = new LibBranchDAO(conn);
		List<BookCopy> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(bdao.getBookById(rs.getInt("bookId")));
			bookCopy.setBranch(branchdao.readLibBranchByPK(rs.getInt("branchId")));
			bookCopy.setCopieNumbersInBranch(rs.getInt("noOfCopies"));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}
	
	@Override
	public List<BookCopy> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		LibBranchDAO branchdao = new LibBranchDAO(conn);
		BookDAO bdao = new BookDAO(conn);
		List<BookCopy> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(bdao.getBookById(rs.getInt("bookId")));
			bookCopy.setBranch(branchdao.readFirstLevel("select * from tbl_library_branch where branchId = ?", new Object[]{rs.getInt("branchId")}).get(0));
			bookCopy.setCopieNumbersInBranch(rs.getInt("noOfCopies"));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}

}
