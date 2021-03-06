package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection connection) {
		super(connection);
	}
	
	public Integer addAuthorWithID(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_author (authorName) values(?)", new Object[] { author.getAuthorName() });
	}

	public void addAuthor(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_author (authorName) values(?)", new Object[] { author.getAuthorName() });
	}

	public void editAuthor(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_author set authorName = ? where authorId = ?", new Object[]{author.getAuthorName(), author.getAuthorId()});
	}

	public void deleteAuthor(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_author where authorId = ?", new Object[]{author.getAuthorId()});
	}

	public List<Author> readAllAuthors()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_author", null);
	}
	
	public List<Author> readAllAuthorsByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_author where authorName like ?", new Object[]{searchString});
	}
	
	public Author readAuthorByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException {
		List<Author> authors = read("Select * from tbl_author where authorId = ?", new Object[]{pk});
		if(!authors.isEmpty()){
			return authors.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			author.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[]{author.getAuthorId()}));
			authors.add(author);
		}
		return authors;
	}
	
	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
	
	public void updateAuthorBookRelations(Author author) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Map<Integer, Integer> hm = new HashMap<>();
		if(author.getBooks()!=null&&!author.getBooks().isEmpty()) {
			for(Book b:readAuthorByPK(author.getAuthorId()).getBooks()) {
				hm.put(b.getBookId(), author.getAuthorId());
			}		
			for(Book b:author.getBooks()) {
				if(!hm.containsKey(b.getBookId())) {
					save("insert into tbl_book_authors (bookId, authorId) values (?, ?)",new Object[]{b.getBookId(), author.getAuthorId()});
					System.out.println("INSERTED");
				}
			}
		}
	}

}
