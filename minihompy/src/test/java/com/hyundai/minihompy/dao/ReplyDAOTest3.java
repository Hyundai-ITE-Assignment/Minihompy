package com.hyundai.minihompy.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.minihompy.domain.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyDAOTest3 {
	@Autowired
	private ReplyDAO replyDAO;
		
	@Test
	public void testUpdate() {
		try {
			ReplyDTO dto = new ReplyDTO();
			dto.setBno(0);
			dto.setId("id1");
			dto.setReplyer("name2");
			dto.setReplytext("content1 수정");
			
			log.info(replyDAO.update(dto));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
