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

/*************************************************************
파일명: HomeController.java
기능: 메인화면으로 이동
작성자: 박주영, 유지훈

[코멘트: X]
*************************************************************/
@Controller
@Slf4j
public class HomeController {
	
	// 실행 시 home.html로 이동
	@RequestMapping("/")
	public String home(Model model) {
		log.info("home 실행");
		
		return "home";
	}
	
}




