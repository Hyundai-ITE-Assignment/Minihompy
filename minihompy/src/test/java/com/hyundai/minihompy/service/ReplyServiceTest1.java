package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyServiceTest1 {
	@Autowired
	private ReplyService replyService;
	
	@Test
	public void testGetList() {
		try {
			replyService.getList(1).forEach(
					i -> log.info(i)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
