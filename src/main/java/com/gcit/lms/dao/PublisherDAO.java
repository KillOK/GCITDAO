package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection connection) {
		super(connection);
	}

	public void addPublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values(?,?,?)", new Object[] { publisher.getPubName(), publisher.getPubAddress(),  publisher.getPubPhone()});
		conn.commit();
	}

	public void editPublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_publisher set PublisherName = ?, publisherAddress=?, publisherPhone=? where PublisherId = ?", new Object[]{publisher.getPubName(), publisher.getPubAddress(), publisher.getPubPhone(), publisher.getPublisherId()});
		conn.commit();
	}

	public void deletePublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_publisher where PublisherId = ?", new Object[]{publisher.getPublisherId()});
		conn.commit();
	}

	public List<Publisher> readAllpublishers()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_publisher", null);
	}
	
	public List<Publisher> readAllpublishersByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_publisher where publisherName like ?", new Object[]{searchString});
	}
	
	public Publisher readPublisherByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Publisher> publishers = readFirstLevel("Select * from tbl_publisher where publisherId = ?", new Object[]{pk});
		if(!publishers.isEmpty()){
			return publishers.get(0);
		}else{
			return null;
		}
	}
	
	public Publisher readPublisherByName(String name)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Publisher> publishers = read("Select * from tbl_publisher where publisherName = ?", new Object[]{name});
		if(!publishers.isEmpty()){
			return publishers.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		BookDAO bdao = new BookDAO(conn);
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("PublisherId"));
			publisher.setPubName(rs.getString("PublisherName"));
			publisher.setPubAddress(rs.getString("PublisherAddress"));
			publisher.setPubPhone(rs.getString("PublisherPhone"));
			publisher.setBooks(bdao.read("select * from tbl_book where bookId IN (select bookId from tbl_book where pubId = ?)", new Object[]{publisher.getPublisherId()}));
			publishers.add(publisher);
		}
		return publishers;
	}
	
	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("PublisherId"));
			publisher.setPubName(rs.getString("PublisherName"));
			publisher.setPubAddress(rs.getString("PublisherAddress"));
			publisher.setPubPhone(rs.getString("PublisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}

}
