package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Book;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection connection) {
		super(connection);
	}

	public void addGenre(Genre genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_genre (genre_name) values(?)", new Object[] { genre.getGenreName() });
	}

	public void editGenre(Genre genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[]{genre.getGenreName(), genre.getGenreId()});
	}

	public void deleteGenre(Genre genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_genre where genre_id = ?", new Object[]{genre.getGenreId()});
	}

	public List<Genre> readAllGenres()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_genre", null);
	}
	
	public List<Genre> readAllgenresByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_genre where genre_name like ?", new Object[]{searchString});
	}
	
	public Genre readGenreByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Genre> genres = read("Select * from tbl_genre where genre_id = ?", new Object[]{pk});
		if(!genres.isEmpty()){
			return genres.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genre.setBooks(bdao.read("select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genre_id = ?)", new Object[]{genre.getGenreId()}));
			genres.add(genre);
		}
		return genres;
	}
	
	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}
	
	public void updateGenreBookRelations(Genre genre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Map<Integer, Integer> hm = new HashMap<>();
		if(genre.getBooks()!=null&&!genre.getBooks().isEmpty()) {
			for(Book b:readGenreByPK(genre.getGenreId()).getBooks()) {
				hm.put(b.getBookId(), genre.getGenreId());
			}		
			for(Book b:genre.getBooks()) {
				if(!hm.containsKey(b.getBookId())) {
					save("insert into tbl_book_genres (bookId, genre_id) values (?, ?)",new Object[]{b.getBookId(), genre.getGenreId()});
					System.out.println("INSERTED");
				}
			}
		}
	}

}
