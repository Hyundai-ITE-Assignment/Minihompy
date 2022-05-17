package com.hyundai.minihompy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.minihompy.dao.ReplyDAO;
import com.hyundai.minihompy.domain.ReplyDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*************************************************************
파일명: ReplyService.java
기능: 댓글 관련 Service
작성자: 박주영

[코멘트: X]
*************************************************************/
@Log4j2
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	// DAO 자동주입
	@Autowired
	private ReplyDAO replyDAO;
	
	// 게시글의 댓글 목록 반환
	@Override
	public List<ReplyDTO> getList(int bno) throws Exception {
		try {
			return replyDAO.getList(bno);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 댓글 작성
	@Override
	public void insert(ReplyDTO replyDTO) throws Exception {
		try {
			replyDAO.insert(replyDTO);
			log.info("댓글이 등록되었습니다.");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 댓글 수정
	@Override
	public int update(ReplyDTO replyDTO) throws Exception {
		try {
			int cnt = replyDAO.update(replyDTO);
			return cnt;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 댓글 삭제 
	@Override
	public int delete(int rno) throws Exception {
		try {
			int cnt = replyDAO.delete(rno);
			return cnt;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

}
