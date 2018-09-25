package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> {

	public BookLoanDAO(Connection connection) {
		super(connection);
	}

	public void addBookLoan(BookLoan bookLoan)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dueDate, dateOut) values(?,?,?,?,?)", new Object[] { bookLoan.getBook().getBookId(),bookLoan.getBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),bookLoan.getDueDate(),bookLoan.getDateOut() });
	}

	public void editBookLoanDate(BookLoan bookLoan)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dueDate=?, dateOut=?, dateIn=? where bookId=? and branchId=? and cardNo=? and dateOut=?", new Object[]{bookLoan.getDueDate(), bookLoan.getDateOut(), bookLoan.getDateIn(),bookLoan.getBook().getBookId(),bookLoan.getBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),bookLoan.getDateOut() });
	}

	public void deleteBookLoan(BookLoan bookLoan)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book_loans where bookId=? and branchId=? and cardNo=? and dueDate=? ", new Object[]{ bookLoan.getBook().getBookId(),bookLoan.getBranch().getBranchId(),bookLoan.getBorrower().getCardNo(),bookLoan.getDueDate() });
	}

	public List<BookLoan> readAllbookLoans()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_loans", null);
	}
	
	public List<BookLoan> readAllbookLoansByBorrower(int borrowerId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_loans where cardNo = ?", new Object[]{borrowerId});
	}
	
	public List<BookLoan> readAllbookLoansByBranch(int branchId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book_loans where branchId = ?", new Object[]{branchId});
	}
	
	public BookLoan readBookLoanByPK(int bookId, int branchId, int cardNo, Date dueDate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookLoan> bookLoans = read("Select * from tbl_book_loans where bookId=? and branchId=? and cardNo=? and dueDate=?", new Object[]{bookId, branchId, cardNo, dueDate});
		if(bookLoans!=null){
			return bookLoans.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		BorrowerDAO bordao = new BorrowerDAO(conn);
		LibBranchDAO branchdao = new  LibBranchDAO(conn);
		List<BookLoan> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBorrower(bordao.readBorrowerByPK(rs.getInt("cardNo")));
			bookLoan.setBranch(branchdao.readLibBranchByPK(rs.getInt("branchId")));
			bookLoan.setBook(bdao.getBookById(rs.getInt("bookId")));
			bookLoan.setDateOut(rs.getDate("dateOut"));
			bookLoan.setDueDate(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}
	
	@Override
	public List<BookLoan> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<BookLoan> bookLoans = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		BorrowerDAO bordao = new BorrowerDAO(conn);
		LibBranchDAO branchdao = new  LibBranchDAO(conn);
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBorrower(bordao.readFirstLevel("select * from tbl_borrower where cardNo = ?",new Object[]{rs.getInt("cardNo")}).get(0));
			bookLoan.setBranch(branchdao.readFirstLevel("select * from tbl_library_branch where branchId = ?",new Object[]{rs.getInt("branchId")}).get(0));
			bookLoan.setBook(bdao.getBookById(rs.getInt("bookId")));
			bookLoan.setDateOut(rs.getDate("dateOut"));
			bookLoan.setDueDate(rs.getDate("dueDate"));
			bookLoan.setDateIn(rs.getDate("dateIn"));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

}
