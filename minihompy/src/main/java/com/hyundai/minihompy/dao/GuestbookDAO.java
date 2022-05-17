package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

/*************************************************************
파일명: GuestbookDAO.java
기능: 방명록 관련 DAO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Mapper
public interface GuestbookDAO {
	
	// 페이지별 방명록 목록 조회
	public List<GuestbookDTO> selectByPage(Pager pager) throws SQLException;
	
	// 총 방명록 수 조회
	public int count() throws SQLException;
	
	// 방명록 insert
	public void insert(GuestbookDTO guestbookDTO) throws SQLException;
	
	// 방명록 update
	public int update(GuestbookDTO guestbookDTO) throws SQLException;

	// 방명록 delete
	public int delete(long bno) throws SQLException;
	
}
