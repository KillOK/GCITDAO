package com.gcit.lms.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.ConnectionUtil;

public class PublisherDAOTest {
	
	@Ignore
	@Test
	public void testAddPublisher() {
//		fail("Not yet implemented");
		try {
			String name = "Skazka";
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			Publisher p = new Publisher();
			p.setPubName(name);
			pdao .addPublisher(p);
			assertEquals(null, name, pdao.readPublisherByName(name).getPubName());
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testEditPublisher() {
		try {
			String name = "FairyTales";
			String newName = "RedDragon";
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			Publisher p = pdao.readPublisherByName(name);
			p.setPubName(newName);
			pdao.editPublisher(p);
			assertEquals(null, newName, pdao.readPublisherByPK(p.getPublisherId()).getPubName());
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testDeletePublisher() {
		try {
			String newName = "Skazka";
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			Publisher p = pdao.readPublisherByName(newName);
			pdao.deletePublisher(p);
			assertEquals(null, null, pdao.readPublisherByPK(p.getPublisherId()));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAllpublishers() {
		try {
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			assertEquals(null, Publisher.class, pdao.readAllpublishers().get(0).getClass());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAllpublishersByName() {
		try {
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			assertEquals(null,"Skazka" , pdao.readAllpublishersByName("Skazka").get(0).getPubName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadPublisherByPK() {
		try {
			PublisherDAO pdao = new PublisherDAO(new ConnectionUtil().getConnection());
			assertEquals(null,"Skazka" , pdao.readPublisherByPK(9).getPubName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testExtractDataResultSet() {
		
	}

	@Test
	public void testExtractDataFirstLevelResultSet() {
		try {
			Connection conn = new ConnectionUtil().getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			ResultSet rs = conn.prepareStatement("SELECT * FROM tbl_publisher").executeQuery();
			assertEquals(null, Publisher.class , pdao.extractDataFirstLevel(rs).get(0).getClass());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
