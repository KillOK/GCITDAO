package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection connection) {
		super(connection);
	}
	
	public Integer addBorrowerWithID(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_borrower (name, address, phone) values(?,?,?)", new Object[] { borrower.getName(), borrower.getAdress(), borrower.getPhone() });
	}

	public void addBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_borrower (name, address, phone) values(?,?,?)", new Object[] { borrower.getName(), borrower.getAdress(), borrower.getPhone() });
	}

	public void editBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ?, address =?, phone = ? where cardNo = ?", new Object[]{borrower.getName(),borrower.getAdress(),borrower.getPhone(),borrower.getCardNo()});
	}

	public void deleteBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[]{borrower.getCardNo()});
	}

	public List<Borrower> readAllBorrowers()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_borrower", null);
	}
	
	public List<Borrower> readAllBorrowersByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_borrower where name like ?", new Object[]{searchString});
	}
	
	public Borrower readBorrowerByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException {
		List<Borrower> borrowers = read("Select * from tbl_borrower where cardNo = ?", new Object[]{pk});
		if(!borrowers.isEmpty()){
			return borrowers.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookLoanDAO loandao = new BookLoanDAO(conn);
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAdress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrower.setBookLoans(loandao.readFirstLevel("select * from tbl_book_loans where cardNo = ?", new Object[]{rs.getInt("cardNo")}));
			borrowers.add(borrower);
		}
		return borrowers;
	}
	
	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAdress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}
