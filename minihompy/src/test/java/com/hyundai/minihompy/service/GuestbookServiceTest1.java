package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.Pager;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class GuestbookServiceTest1 {
	@Autowired
	private GuestbookService gusetbookService;
	
	@Test
	public void testGetList() {
		try {
			int totalRows = gusetbookService.getCount();
			Pager pager = new Pager(5, 5, totalRows, 1);
			gusetbookService.getList(pager).forEach(
					i -> log.info(i)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
