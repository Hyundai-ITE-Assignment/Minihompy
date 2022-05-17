package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.Pager;

@Mapper
public interface BoardDAO {
	
	public List<BoardDTO> selectByPage(Pager pager) throws SQLException;
	
	public int count() throws SQLException;
	
	public BoardDTO getDetail(long bno) throws SQLException;
	
	public void insert(BoardDTO boardDTO) throws SQLException;
	
	public int update(BoardDTO boardDTO) throws SQLException;

	public int delete(long bno) throws SQLException;
	
	public int updateHitcount(long bno) throws SQLException;
	
	public int totalHitcount(String id) throws SQLException;
}
