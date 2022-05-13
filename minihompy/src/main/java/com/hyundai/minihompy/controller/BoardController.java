package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("insert")
	public String insert() {
		return "board/insert";
	}
	
	@PostMapping("insert")
	public String insert(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		
		try {
			boardDTO.setId("user1");
			boardService.insert(boardDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물 등록 오류입니다.");
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		}
	}
	
	@GetMapping("list")
	public String list(Model model) throws Exception {
		try {
			List<BoardDTO> list = boardService.getList();
			model.addAttribute("list", list);
			return "board/list";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("detail")
	public String detail(@RequestParam(defaultValue = "0") long bno, Model model) throws Exception {
		try {
			BoardDTO boardDTO = boardService.getDetail(bno);
			model.addAttribute("boardDTO", boardDTO);
			return "board/detail";
		} catch (Exception e) {
			model.addAttribute("msg", "접근할 수 없는 게시물이거나 시스템 오류입니다.");
			model.addAttribute("url", "list");
			return "board/result";
		}
	}
	
	@GetMapping("delete")
	public String goDelete(@RequestParam long bno, Model model) {
		try {
			model.addAttribute("bno", bno);
			return "board/delete";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam(defaultValue = "0") long bno, Model model) {
		log.info(bno);
		try {
			boardService.delete(bno);
			model.addAttribute("msg", bno + "번 글이 삭제 되었습니다.");
			model.addAttribute("url", "list");
			return "board/result";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		}
	}
	
	@GetMapping("update")
	public String goUpdate(@RequestParam long bno, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetail(bno);
			model.addAttribute("boardDTO", boardDTO);
			return "board/update";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		}
	}

	@PostMapping("update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		try {
			boardService.update(boardDTO);
			return "redirect:detail?bno=" + boardDTO.getBno();
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		}
	}
	
}
