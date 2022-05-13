package com.hyundai.minihompy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyServiceTest2 {
	@Autowired
	private ReplyService replyService;
	
	@Test
	public void testInsert() {
		try {
			ReplyDTO dto = new ReplyDTO();
			dto.setBno(0);
			dto.setId("id1");
			dto.setReplyer("name2");
			dto.setReplytext("content1");
			
			log.info(dto);
			replyService.insert(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
