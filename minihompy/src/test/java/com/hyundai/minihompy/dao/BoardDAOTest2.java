package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.BoardDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardDAOTest2 {
	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	public void testInsert() {
		try {
			BoardDTO dto = new BoardDTO();
			dto.setId("id1");
			dto.setName("name2");
			dto.setTitle("title1");
			dto.setContent("content1");
			
			log.info(dto);
			boardDAO.insert(dto);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
