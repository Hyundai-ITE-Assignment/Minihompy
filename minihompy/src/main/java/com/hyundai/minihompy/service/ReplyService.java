package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.ReplyDTO;

/*************************************************************
파일명: ReplyService.java
기능: 댓글 관련 Service 인터페이스
작성자: 박주영

[코멘트: X]
*************************************************************/
public interface ReplyService {
	
	// 게시글의 댓글 목록 반환
	List<ReplyDTO> getList(int bno) throws Exception;
	
	// 댓글 작성
	void insert(ReplyDTO replyDTO) throws Exception;
	
	// 댓글 수정
	int update(ReplyDTO replyDTO) throws Exception;
		
	// 댓글 삭제
	int delete(int rno) throws Exception;
}
