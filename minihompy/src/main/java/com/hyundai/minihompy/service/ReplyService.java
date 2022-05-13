package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.ReplyDTO;

public interface ReplyService {
	
	List<ReplyDTO> getList(int bno) throws Exception;
	
	void insert(ReplyDTO replyDTO) throws Exception;
	
	int update(ReplyDTO replyDTO) throws Exception;
		
	int delete(int rno) throws Exception;
}
