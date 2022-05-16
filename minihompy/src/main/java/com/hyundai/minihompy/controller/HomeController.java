package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		log.info("home 실행");
		return "home";
	}//end ho..
	
	
}//end class




