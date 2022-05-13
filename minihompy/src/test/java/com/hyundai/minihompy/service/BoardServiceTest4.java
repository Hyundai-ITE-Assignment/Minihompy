package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardServiceTest4 {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testDelete() {
		try {
			boardService.delete(1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
