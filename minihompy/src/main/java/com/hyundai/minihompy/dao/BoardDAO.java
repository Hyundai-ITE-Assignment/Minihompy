package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.BoardDTO;

@Mapper
public interface BoardDAO {
	
	void insert(BoardDTO boardDTO) throws SQLException;
	
	List<BoardDTO> getList() throws SQLException;
	
	BoardDTO getDetail(long bno) throws SQLException;
	
	int delete(long bno) throws SQLException;
	
	int update(BoardDTO boardDTO) throws SQLException;
	
}
