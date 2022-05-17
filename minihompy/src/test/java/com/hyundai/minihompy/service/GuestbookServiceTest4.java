package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestbookServiceTest4 {
	
	@Autowired
	private GuestbookService gusetbookService;
	
	@Test
	public void testDelete() {
		try {
			gusetbookService.delete(1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
