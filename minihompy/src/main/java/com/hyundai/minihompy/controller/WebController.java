package com.hyundai.minihompy.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.Pager;
import com.hyundai.minihompy.security.jwt.TokenProvider;
import com.hyundai.minihompy.service.BoardService;
import com.hyundai.minihompy.service.GuestbookService;
import com.hyundai.minihompy.service.MemberService;

import lombok.extern.log4j.Log4j2;

/*************************************************************
파일명: WebController.java
기능: 페이지 이동 및 모델 담긴 뷰 반환
작성자: 박주영, 유지훈

[코멘트: JWT 토큰 인증 사용]
*************************************************************/
@Log4j2
@Controller
public class WebController {

	// 필요한 Service 및 Provider 자동 주입
	@Autowired
	private BoardService boardService;

	@Autowired
	private GuestbookService guestbookService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private TokenProvider provider;

	// 게시글 목록 조회 - 누구나 가능 (박주영)
	@PostMapping("/board")
	public String getList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(value="Authorization") String token,
			Model model) throws Exception {
		try {
			// JWT 토큰으로 검증
			token = token.substring(7, token.length()-1);
			log.info("token: " + token);
			
			if (!provider.validateToken(token)) { // 검증 실패
				throw new IllegalArgumentException("유효하지 않은 토큰");
			} else { // 검증 성공
				Authentication auth = provider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth); //유효성 검증을 하고 정상 토큰이면 Security Context에 저장
				log.info("Security Context에 '{}' 인증 정보를 저장했습니다", auth.getName());
			}

			// 게시물 총 개수
			int totalRows = boardService.getCount();
			// 페이징 생성
			Pager pager = new Pager(5, 5, totalRows, pageNo);
			// 게시판 페이지 가져오기
			List<BoardDTO> list = boardService.getList(pager);
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);

			log.info(pager);
			
			// list.html로 이동
			return "board/list";

		} catch (Exception e) {
			throw e;
		}
	}

	// 게시글 상세 조회 - 누구나 가능 (박주영)
	@PostMapping("/board/detail")
	public String detail(@RequestParam(value="bno") long bno, @RequestParam(value="Authorization") String token, Model model) throws Exception {
		try {
			// JWT 토큰으로 검증
			token = token.substring(7, token.length()-1);
			log.info("token: " + token);
			
			if (!provider.validateToken(token)) { // 검증 실패
				throw new IllegalArgumentException("유효하지 않은 토큰");
			} else { // 검증 성공
				Authentication auth = provider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth); //유효성 검증을 하고 정상 토큰이면 Security Context에 저장
				log.info("Security Context에 '{}' 인증 정보를 저장했습니다", auth.getName());
			}
			
			// 조회수 +1 증가
			boardService.updateHitcount(bno);

			// 게시글 상세 정보 조회
			BoardDTO boardDTO = boardService.getDetail(bno);
			model.addAttribute("boardDTO", boardDTO);

			// detail.html로 이동
			return "board/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 게시글 작성 페이지로 이동 - ADMIN만 버튼이 보임 (박주영)
	@GetMapping("/board/insert")
	public String goInsert() {
		// insert.html로 이동
		return "board/insert";
	}

	// 게시글 수정 페이지로 이동 - ADMIN반 버튼이 보임 (박주영)
	@GetMapping("/board/update")
	public String goUpdate(@RequestParam long bno, Model model) {
		try {
			BoardDTO boardDTO = boardService.getDetail(bno);
			
			// 게시글 내용 개행 치환 처리
			String contents = boardDTO.getContent().replace("<br>", "\r\n");
			boardDTO.setContent(contents);
			
			model.addAttribute("boardDTO", boardDTO);
			
			// update.html로 이동
			return "board/update";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 방명록 목록 조회 - 누구나 가능 (박주영)
	@PostMapping("/guestbook")
	public String getGuestbookList(@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(value="Authorization") String token, Model model) throws Exception {
		try {
			// JWT 토큰으로 검증
			token = token.substring(7, token.length()-1);
			log.info("token: " + token);
			
			if (!provider.validateToken(token)) { // 검증 실패
				throw new IllegalArgumentException("유효하지 않은 토큰");
			} else { // 검증 성공
				Authentication auth = provider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth); //유효성 검증을 하고 정상 토큰이면 Security Context에 저장
				log.info("Security Context에 '{}' 인증 정보를 저장했습니다", auth.getName());
				
				// 인증 정보로 name 넘겨주기
				String userName = memberService.findById(auth.getName(), 0).getName();
				model.addAttribute("user", userName);
			}
			
			// 방명록 총 개수
			int totalRows = guestbookService.getCount();
			// 페이징 생성
			Pager pager = new Pager(100, 5, totalRows, pageNo);
			// 방명록 페이지 가져오기
			List<GuestbookDTO> list = guestbookService.getList(pager);
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);

			log.info(pager);
			
			// guestbook/list.html로 이동
			return "guestbook/list";

		} catch (Exception e) {
			throw e;
		}
	}

	// 회원 가입 후 로그인 창으로 이동하기 (유지훈)
	@PreAuthorize("permitAll()")
	@PostMapping(path = "/members/signup")
	public String signUp(@RequestBody MemberDTO memberDTO) throws SQLException {
		memberService.insertMember(memberDTO);
		
		// login.html로 이동
		return "member/login";
	}

	// 회원가입 페이지 이동 (유지훈)
	@GetMapping("/members/signup")
	public String getSignupPage() {
		
		// signup.html로 이동
		return "member/signup";
	}

	// 로그인 페이지 이동 (유지훈)
	@GetMapping("/members/login")
	public String getLogin() {
		
		// login.html로 이동
		return "member/login";
	}
}
