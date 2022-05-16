package com.hyundai.minihompy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.service.GuestbookService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	// 게시글 작성
	@PostMapping("/insert")
	public GuestbookDTO insert(GuestbookDTO guestbookDTO, Model model) throws Exception {
		log.info(guestbookDTO.toString());

		try {
			guestbookDTO.setId("user1");
			guestbookDTO.setName("김땡땡");

			guestbookService.insert(guestbookDTO);
			log.info(guestbookDTO);
			
			return guestbookDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 수정
	@PutMapping("/update")
	public GuestbookDTO update(GuestbookDTO guestbookDTO, Model model) throws Exception {
		try {
			guestbookDTO.setId("user");
			guestbookDTO.setName("김땡땡");

			guestbookService.update(guestbookDTO);
			log.info(guestbookDTO);
			
			return guestbookDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 삭제
	@DeleteMapping("/{gno}")
	public Map<String, String> delete(@PathVariable long gno, Model model)  throws Exception{
		try {
			guestbookService.delete(gno);
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "SUCCESS");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
