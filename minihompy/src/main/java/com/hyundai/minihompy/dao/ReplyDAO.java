package com.hyundai.minihompy.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.minihompy.domain.ReplyDTO;

@Mapper
public interface ReplyDAO {

	List<ReplyDTO> getList(int bno) throws SQLException;
	
	void insert(ReplyDTO replyDTO) throws SQLException;
	
	int update(ReplyDTO replyDTO) throws SQLException;
		
	int delete(int rno) throws SQLException;
	

}
