package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.BoardDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardServiceTest3 {
	@Autowired
	private BoardService boardService;
		
	@Test
	public void testUpdate() {
		try {
			BoardDTO dto = new BoardDTO();
			dto.setId("id1");
			dto.setName("name2");
			dto.setTitle("title1 수정");
			dto.setContent("content1 수정");
			
			log.info(dto);
			boardService.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
