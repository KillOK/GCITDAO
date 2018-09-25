package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.LibBranch;

public class LibBranchDAO extends BaseDAO<LibBranch> {

	public LibBranchDAO(Connection connection) {
		super(connection);
	}
	
	public Integer addLibBranchWithID(LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_library_branch (branchName,branchAddress) values(?,?)", new Object[] { libBranch.getBranchName(), libBranch.getBranchAdress() });
	}

	public void addLibBranch(LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_library_branch (branchName,branchAddress) values(?,?)", new Object[] { libBranch.getBranchName(), libBranch.getBranchAdress() });
	}

	public void editLibBranch(LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_library_branch set branchName = ?, branchAddress=? where branchId = ?", new Object[]{ libBranch.getBranchName(), libBranch.getBranchAdress(), libBranch.getBranchId() });
	}

	public void deleteLibBranch(LibBranch libBranch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_library_branch where branchId = ?", new Object[]{libBranch.getBranchId()});
	}

	public List<LibBranch> readAllLibBranches()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_library_branch", null);
	}
	
	public List<LibBranch> readAllLibBranchsByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_library_branch where branchName like ?", new Object[]{searchString});
	}
	
	public LibBranch readLibBranchByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException {
		List<LibBranch> libBranches = read("Select * from tbl_library_branch where branchId = ?", new Object[]{pk});
		if(!libBranches.isEmpty()){
			return libBranches.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<LibBranch> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookCopyDAO bcdao = new BookCopyDAO(conn);
		BookLoanDAO loandao = new BookLoanDAO(conn);
		List<LibBranch> libBranches = new ArrayList<>();
		while (rs.next()) {
			LibBranch libBranch = new LibBranch();
			libBranch.setBranchId(rs.getInt("branchId"));
			libBranch.setBranchName(rs.getString("branchName"));
			libBranch.setBranchAdress(rs.getString("branchAddress"));
			libBranch.setBookCopies(bcdao.readFirstLevel("select * from tbl_book_copies where branchId = ?", new Object[]{libBranch.getBranchId()}));
			libBranch.setBookLoans(loandao.readFirstLevel("select * from tbl_book_loans where branchId = ?", new Object[]{libBranch.getBranchId()}));
			libBranches.add(libBranch);
		}
		return libBranches;
	}
	
	@Override
	public List<LibBranch> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<LibBranch> libBranches = new ArrayList<>();
		while (rs.next()) {
			LibBranch libBranch = new LibBranch();
			libBranch.setBranchId(rs.getInt("branchId"));
			libBranch.setBranchName(rs.getString("branchName"));
			libBranch.setBranchAdress(rs.getString("branchAddress"));
			libBranches.add(libBranch);
		}
		return libBranches;
	}

}
