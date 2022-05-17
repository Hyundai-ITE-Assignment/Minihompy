package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.GuestbookDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestbookDAOTest4 {
	@Autowired
	private GuestbookDAO guestbookDAO;
	
	@Test
	public void testInsert() {
		try {
			GuestbookDTO dto = new GuestbookDTO();
			dto.setId("id1");
			dto.setName("name2");
			dto.setContent("content1");
			
			log.info(dto);
			guestbookDAO.insert(dto);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
