package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.Pager;

/*************************************************************
파일명: BoardDAO.java
기능: 게시판 관련 DAO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Mapper
public interface BoardDAO {
	
	// 페이지 별 게시글 목록 조회
	public List<BoardDTO> selectByPage(Pager pager) throws SQLException;
	
	// 총 게시글 수 조회
	public int count() throws SQLException;
	
	// 게시글 상세 정보 반환
	public BoardDTO getDetail(long bno) throws SQLException;
	
	// 게시글 insert
	public void insert(BoardDTO boardDTO) throws SQLException;
	
	// 게시글 update
	public int update(BoardDTO boardDTO) throws SQLException;

	// 게시글 delete
	public int delete(long bno) throws SQLException;
	
	// 게시글 조회수 update
	public int updateHitcount(long bno) throws SQLException;
	
	// 게시글 총 조회수 조회
	public int totalHitcount() throws SQLException;
}
