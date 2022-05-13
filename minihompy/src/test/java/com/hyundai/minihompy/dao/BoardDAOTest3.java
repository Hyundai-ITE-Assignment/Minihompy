package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardDAOTest3 {
	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	public void testGetDetail() {
		try {
			log.info(boardDAO.getDetail(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
