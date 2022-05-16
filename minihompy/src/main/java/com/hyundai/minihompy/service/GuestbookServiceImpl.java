package com.hyundai.minihompy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.minihompy.dao.GuestbookDAO;
import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookDAO guestbookDAO;

	@Override
	public List<GuestbookDTO> getList(Pager pager) throws Exception {
		try {
			return guestbookDAO.selectByPage(pager);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public int getCount() throws Exception {
		return guestbookDAO.count();
	}


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
