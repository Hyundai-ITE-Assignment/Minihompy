package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.Pager;
import com.hyundai.minihompy.service.BoardService;
import com.hyundai.minihompy.service.GuestbookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class WebController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private GuestbookService guestbookService;
	
	// 게시글 목록 조회
	@GetMapping("/board")
	public String getList(@RequestParam(defaultValue = "1") int pageNo, Model model) throws Exception {
		try {
			// 게시물 총 개수
			int totalRows = boardService.getCount();
			// 페이징 생성
			Pager pager = new Pager(5, 5, totalRows, pageNo);
			// 게시판 페이지 가져오기
			List<BoardDTO> list = boardService.getList(pager);
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);
			
			log.info(pager);
			return "board/list";
			
		} catch (Exception e) {
			throw e;
		}
	}
	

	// 게시글 상세 조회
	@GetMapping("/board/detail")
	public String detail(@RequestParam(defaultValue = "0") long bno, Model model) throws Exception {
		try {
			// 조회수 +1 증가
			boardService.updateHitcount(bno);
			
			BoardDTO boardDTO = boardService.getDetail(bno);
			model.addAttribute("boardDTO", boardDTO);
			
			return "board/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 게시글 작성 페이지로 이동
	@GetMapping("/board/insert")
	public String goInsert() {
		return "board/insert";
	}

	// 게시글 수정 페이지로 이동
	@GetMapping("/board/update")
	public String goUpdate(@RequestParam long bno, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetail(bno);
			boardDTO.setName("유저1");
			// 게시글 내용 개행 치환 처리
			String contents = boardDTO.getContent().replace("<br>", "\r\n");
			boardDTO.setContent(contents);
			model.addAttribute("boardDTO", boardDTO);
			return "board/update";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	// 방명록 목록 조회
	@GetMapping("/guestbook")
	public String getGuestbookList(@RequestParam(defaultValue = "1") int pageNo, Model model) throws Exception {
		try {
			// 방명록 총 개수
			int totalRows = guestbookService.getCount();
			// 페이징 생성
			Pager pager = new Pager(5, 5, totalRows, pageNo);
			// 방명록 페이지 가져오기
			List<GuestbookDTO> list = guestbookService.getList(pager);
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);
			
			log.info(pager);
			return "guestbook/list";
			
		} catch (Exception e) {
			throw e;
		}
	}
}
