package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.ReplyDTO;

/*************************************************************
파일명: ReplyDAO.java
기능: 댓글 관련 DAO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Mapper
public interface ReplyDAO {

	// 게시글의 댓글 목록 조회
	List<ReplyDTO> getList(int bno) throws SQLException;
	
	// 댓글 insert
	void insert(ReplyDTO replyDTO) throws SQLException;
	
	// 댓글 update
	int update(ReplyDTO replyDTO) throws SQLException;
		
	// 댓글 delete
	int delete(int rno) throws SQLException;
	

}
