package com.hyundai.minihompy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.minihompy.dao.GuestbookDAO;
import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

import lombok.extern.log4j.Log4j2;

/*************************************************************
파일명: GuestbookServiceImpl.java
기능: 방명록 관련 Service
작성자: 박주영

[코멘트: X]
*************************************************************/
@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

	// DAO 자동주입
	@Autowired
	private GuestbookDAO guestbookDAO;

	// 페이지별 방명록 목록 반환
	@Override
	public List<GuestbookDTO> getList(Pager pager) throws Exception {
		try {
			return guestbookDAO.selectByPage(pager);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	// 총 방명록 수 반환
	@Override
	public int getCount() throws Exception {
		return guestbookDAO.count();
	}


	// 방명록 작성
	@Override
	public void insert(GuestbookDTO guestbookDTO) throws Exception {
		try {
			guestbookDAO.insert(guestbookDTO);
			log.info("방명록이 등록되었습니다.");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 방명록 수정
	@Override
	public void update(GuestbookDTO guestbookDTO) throws Exception {
		try {
			if(guestbookDAO.update(guestbookDTO) == 0) {
				throw new RuntimeException("방명록이 존재하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	// 방명록 삭제
	@Override
	public void delete(long bno) throws Exception {
		try {
			guestbookDAO.delete(bno);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}


}
