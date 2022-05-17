package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

/*************************************************************
파일명: GuestbookService.java
기능: 방명록 관련 Service 인터페이스
작성자: 박주영

[코멘트: X]
*************************************************************/
public interface GuestbookService {
	
	// 페이지별 방명록 목록 반환
	public List<GuestbookDTO> getList(Pager pager) throws Exception;
	
	// 총 방명록 수 반환
	public int getCount() throws Exception;
	
	// 방명록 작성
	public void insert(GuestbookDTO guestbookDTO) throws Exception;
	
	// 방명록 수정
	public void update(GuestbookDTO guestbookDTO) throws Exception;

	// 방명록 삭제
	public void delete(long bno) throws Exception;
	
}
