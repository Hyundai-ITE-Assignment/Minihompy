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
	
	public List<BoardDTO> selectByPage(Pager pager) throws SQLException;
	
	public int count() throws SQLException;
	
	public BoardDTO getDetail(long bno) throws SQLException;
	
	public void insert(BoardDTO boardDTO) throws SQLException;
	
	public int update(BoardDTO boardDTO) throws SQLException;

	public int delete(long bno) throws SQLException;
	
	public int updateHitcount(long bno) throws SQLException;
	
	public int totalHitcount() throws SQLException;
}
