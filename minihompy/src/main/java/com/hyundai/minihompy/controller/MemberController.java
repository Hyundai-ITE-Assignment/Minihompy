package com.hyundai.minihompy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.service.BoardService;
import com.hyundai.minihompy.service.MemberService;

import lombok.extern.log4j.Log4j2;

/*************************************************************
 * 파일명: MemberController.java
 * 기능: 회원 정보 조회, 총 조회수 조회, 도토리 충전
 * 작성자: 박주영, 유지훈
 * 
 * [코멘트: X]
 *************************************************************/
@RestController
@Log4j2
public class MemberController {

	private final MemberService memberService;
	private final BoardService boardService;

	public MemberController(MemberService memberService, BoardService boardService) {
		this.memberService = memberService;
		this.boardService = boardService;
	}

	// 회원 정보 조회 (유지훈)
	@GetMapping(path = "/members/info")
	public MemberDTO memberInfo(@AuthenticationPrincipal User authentication) {
		log.info("로그인한 아이디 : " + authentication.getUsername());
		
		// 인증 정보로 회원 정보 조회
		MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
		log.info(memberDTO);
		
		// 회원 정보 반환
		return memberDTO;
	}

	// 총 게시글 조회수 반환 (박주영)
	@GetMapping("/members/hitcounts")
	public int getHitcounts() {
		int hitcounts = 0;
		try {
			// 총 게시글 조회수 조회
			hitcounts = boardService.totalHitcount();
			log.info("hitcounts : " + hitcounts);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hitcounts;
	}

	// 도토리 10씩 충전 (박주영)
	@GetMapping("/members/dotori")
	public void addDotori(@AuthenticationPrincipal User authentication) {
		log.info("도토리 충전!");

		// 도토리 +10
		memberService.addDotori(authentication.getUsername());
	}

}
