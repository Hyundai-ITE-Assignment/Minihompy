package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyDAOTest1 {
	@Autowired
	private ReplyDAO replyDAO;
	
	@Test
	public void testGetList() {
		try {
			replyDAO.getList(1).forEach(
					i -> log.info(i)
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
