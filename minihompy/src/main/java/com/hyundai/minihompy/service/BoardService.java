package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.BoardDTO;

public interface BoardService {
	
	void insert(BoardDTO boardDTO) throws Exception;
	
	List<BoardDTO> getList() throws Exception;
	
	BoardDTO getDetail(long bno) throws Exception;
	
	void delete(long bno) throws Exception;
	
	void update(BoardDTO boardDTO) throws Exception;
	
}
