package com.hyundai.minihompy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.minihompy.dao.ReplyDAO;
import com.hyundai.minihompy.domain.ReplyDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public List<ReplyDTO> getList(int bno) throws Exception {
		try {
			return replyDAO.getList(bno);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public void insert(ReplyDTO replyDTO) throws Exception {
		try {
			replyDAO.insert(replyDTO);
			log.info("댓글이 등록되었습니다.");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public int update(ReplyDTO replyDTO) throws Exception {
		try {
			int cnt = replyDAO.update(replyDTO);
			return cnt;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int rno) throws Exception {
		try {
			int cnt = replyDAO.delete(rno);
			return cnt;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

}
