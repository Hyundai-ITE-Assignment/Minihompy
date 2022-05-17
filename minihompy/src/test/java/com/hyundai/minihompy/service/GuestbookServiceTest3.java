package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.GuestbookDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestbookServiceTest3 {
	@Autowired
	private GuestbookService gusetbookService;
	
	@Test
	public void testInsert() {
		try {
			GuestbookDTO dto = new GuestbookDTO();
			dto.setId("id1");
			dto.setName("name2");
			dto.setContent("content1");
			
			log.info(dto);
			gusetbookService.insert(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
