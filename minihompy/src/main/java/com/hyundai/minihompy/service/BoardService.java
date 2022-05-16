package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.Pager;

public interface BoardService {
	
	public List<BoardDTO> getList(Pager pager) throws Exception;
	
	public int getCount() throws Exception;
	
	public BoardDTO getDetail(long bno) throws Exception;
	
	public void insert(BoardDTO boardDTO) throws Exception;
	
	public void delete(long bno) throws Exception;
	
	public void update(BoardDTO boardDTO) throws Exception;
	
	public void updateHitcount(long bno) throws Exception;
	
}
