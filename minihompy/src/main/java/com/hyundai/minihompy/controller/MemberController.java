package com.hyundai.minihompy.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.security.jwt.TokenProvider;
import com.hyundai.minihompy.service.BoardService;
import com.hyundai.minihompy.service.MemberService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MemberController {

	private final MemberService memberService;
	private final BoardService boardService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public MemberController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
			MemberService memberService, BoardService boardService) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.memberService = memberService;
		this.boardService = boardService;
	}

	@GetMapping(path = "/members/info")
	public MemberDTO memberInfo(@AuthenticationPrincipal User authentication) {
		log.info("로그인한 아이디 : " + authentication.getUsername());
		MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
		log.info(memberDTO);
		return memberDTO;
	}

	@GetMapping("/members/hitcounts")
	public int getHitcounts(@AuthenticationPrincipal User authentication) {
		int hitcounts = 0;
		try {
			hitcounts = boardService.totalHitcount(authentication.getUsername());
			log.info("hitcounts : " + hitcounts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hitcounts;
	}

	// 도토리 충전
	@GetMapping("/members/dotori")
	public void addDotori(@AuthenticationPrincipal User authentication) {
		log.info("도토리 충전!");

		memberService.addDotori(authentication.getUsername());
	}

}
