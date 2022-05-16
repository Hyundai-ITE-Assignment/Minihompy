package com.hyundai.minihompy.service;

import java.util.List;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;

public interface GuestbookService {
	
	public List<GuestbookDTO> getList(Pager pager) throws Exception;
	
	public int getCount() throws Exception;
	
	public void insert(GuestbookDTO guestbookDTO) throws Exception;
	
	public void update(GuestbookDTO guestbookDTO) throws Exception;

	public void delete(long bno) throws Exception;
	
}
