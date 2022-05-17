package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.ReplyDTO;
import com.hyundai.minihompy.service.MemberService;
import com.hyundai.minihompy.service.ReplyService;

import lombok.extern.log4j.Log4j2;

/*************************************************************
파일명: ReplyController.java
기능: 댓글 조회, 작성, 수정, 삭제
작성자: 박주영

[코멘트: RESTful 방식, Ajax 사용, 'USER'만 접근 가능]
*************************************************************/
@RestController
@Log4j2
@RequestMapping("/replies")
public class ReplyController {
	
	// 필요한 Service 자동주입
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MemberService memberService;
	
	// 댓글 목록 조회 - 누구나 가능
	@GetMapping("/{bno}")
	public List<ReplyDTO> list(@PathVariable("bno") int bno) {
		List<ReplyDTO> list = null;
		try {
			// 해당 게시글 번호를 넘겨 게시글의 댓글 목록 조회
			list = replyService.getList(bno);
			log.info(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 댓글 목록 반환
		return list;
	}
	
	// 댓글 작성 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("")
	public String insert(@RequestBody ReplyDTO dto, @AuthenticationPrincipal User authentication) {
		try {
			// 인증 정보로 id와 name 설정
			dto.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			dto.setReplyer(memberDTO.getName());
			
			// 댓글 insert
			replyService.insert(dto);
			
			// 성공 결과 반환
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 댓글 수정 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public String update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto, @AuthenticationPrincipal User authentication) {
		try {
			// 인증 정보로 id와 name 설정
			dto.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			dto.setReplyer(memberDTO.getName());
			dto.setRno(rno);
			
			// 댓글 update
			replyService.update(dto);
			
			// 성공 결과 반환
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 댓글 삭제 - USER만 가능
	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/{rno}")
	public String delete(@PathVariable("rno") int rno) {
		try {
			// 댓글 delete
			replyService.delete(rno);
			// 성공 결과 반환
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
