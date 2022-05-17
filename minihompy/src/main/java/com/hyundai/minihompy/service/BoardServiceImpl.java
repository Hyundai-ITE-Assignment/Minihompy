package com.hyundai.minihompy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.minihompy.dao.BoardDAO;
import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.Pager;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardDTO> getList(Pager pager) throws Exception {
		try {
			return boardDAO.selectByPage(pager);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public int getCount() throws Exception {
		return boardDAO.count();
	}
	
	@Override
	public BoardDTO getDetail(long bno) throws Exception {
		try {
			BoardDTO boardDTO = boardDAO.getDetail(bno);
			if(boardDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return boardDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public void insert(BoardDTO boardDTO) throws Exception {
		try {
			boardDAO.insert(boardDTO);
			log.info("게시글이 등록되었습니다.");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		try {
			if(boardDAO.update(boardDTO) == 0) {
				throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void delete(long bno) throws Exception {
		try {
			boardDAO.delete(bno);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	
	@Override
	public void updateHitcount(long bno) throws Exception {
		boardDAO.updateHitcount(bno);
		
	}

	@Override
	public int totalHitcount(String id) throws Exception {
		int total = 0;
		try {
			total = boardDAO.totalHitcount(id);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
		return total;
	}

	

}
