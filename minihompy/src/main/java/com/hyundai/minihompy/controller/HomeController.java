package com.hyundai.minihompy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.service.BoardService;
import com.hyundai.minihompy.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/")
	public String home(Model model) {
		log.info("home 실행");
		
		return "home";
	}
	
	
	
}//end class




