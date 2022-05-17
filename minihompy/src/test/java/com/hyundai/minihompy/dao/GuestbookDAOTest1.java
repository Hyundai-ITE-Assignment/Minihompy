package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.Pager;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestbookDAOTest1 {
	@Autowired
	private GuestbookDAO guestbookDAO;
	
	@Test
	public void testGetList() {
		try {
			int totalRows = guestbookDAO.count(); 
			Pager pager = new Pager(5, 5, totalRows, 1);
			guestbookDAO.selectByPage(pager).forEach(
					i -> log.info(i)
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
