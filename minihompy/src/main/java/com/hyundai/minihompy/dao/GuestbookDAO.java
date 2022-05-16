package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

@Mapper
public interface GuestbookDAO {
	
	public List<GuestbookDTO> selectByPage(Pager pager) throws SQLException;
	
	public int count() throws SQLException;
	
	public void insert(GuestbookDTO guestbookDTO) throws SQLException;
	
	public int update(GuestbookDTO guestbookDTO) throws SQLException;

	public int delete(long bno) throws SQLException;
	
}
