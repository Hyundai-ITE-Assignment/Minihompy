package com.hyundai.minihompy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.GuestbookDTO;
import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.service.GuestbookService;
import com.hyundai.minihompy.service.MemberService;

import lombok.extern.log4j.Log4j2;

/*************************************************************
 * 파일명: GuestbookController.java
 * 기능: 방명록 작성, 수정, 삭제
 * 작성자: 박주영
 * 
 * [코멘트: RESTful 방식, Ajax 사용, 'USER'만 접근 가능]
 *************************************************************/
@RestController
@RequestMapping("/guestbook")
@Log4j2
public class GuestbookController {

	// 필요한 Service 자동주입
	@Autowired
	private GuestbookService guestbookService;

	@Autowired
	private MemberService memberService;

	// 방명록 작성 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/insert")
	public GuestbookDTO insert(GuestbookDTO guestbookDTO, Model model, @AuthenticationPrincipal User authentication)
			throws Exception {
		log.info(guestbookDTO.toString());

		try {
			// 인증 정보로 id와 name 설정
			guestbookDTO.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			guestbookDTO.setName(memberDTO.getName());

			// 방명록 insert
			guestbookService.insert(guestbookDTO);
			log.info(guestbookDTO);

			// 방명록 반환
			return guestbookDTO;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 수정 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/update")
	public GuestbookDTO update(GuestbookDTO guestbookDTO, Model model, @AuthenticationPrincipal User authentication)
			throws Exception {
		try {
			// 인증 정보로 id와 name 설정
			guestbookDTO.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			guestbookDTO.setName(memberDTO.getName());

			// 방명록 update
			guestbookService.update(guestbookDTO);
			log.info(guestbookDTO);

			// 수정된 방명록 반환
			return guestbookDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 삭제 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/{gno}")
	public Map<String, String> delete(@PathVariable long gno, Model model) throws Exception {
		try {
			// 방명록 삭제
			guestbookService.delete(gno);
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "SUCCESS");
			
			// 성공 결과 반환
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
