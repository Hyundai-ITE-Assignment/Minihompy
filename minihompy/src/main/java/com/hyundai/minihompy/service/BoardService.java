package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.Pager;

/*************************************************************
파일명: BoardService.java
기능: 게시글 관련 Service 인터페이스
작성자: 박주영

[코멘트: X]
*************************************************************/
public interface BoardService {
	
	// 페이지 별 게시글 목록 반환
	public List<BoardDTO> getList(Pager pager) throws Exception;
	
	// 총 게시글 수 반환
	public int getCount() throws Exception;
	
	// 게시글 상세 정보 반환
	public BoardDTO getDetail(long bno) throws Exception;
	
	// 게시글 작성
	public void insert(BoardDTO boardDTO) throws Exception;
	
	// 게시글 삭제
	public void delete(long bno) throws Exception;
	
	// 게시글 수정
	public void update(BoardDTO boardDTO) throws Exception;
	
	// 게시글 조회수 +1
	public void updateHitcount(long bno) throws Exception;
	
	// 게시글 총 조회수 반환
	public int totalHitcount() throws Exception;
}
