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

@RestController
@Log4j2
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/{bno}")
	public List<ReplyDTO> list(@PathVariable("bno") int bno) {
		List<ReplyDTO> list = null;
		try {
			list = replyService.getList(bno);
			log.info(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("")
	public String insert(@RequestBody ReplyDTO dto, @AuthenticationPrincipal User authentication) {
		try {
			log.info(authentication.getAuthorities());
			dto.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			dto.setReplyer(memberDTO.getName());
			replyService.insert(dto);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public String update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto, @AuthenticationPrincipal User authentication) {
		try {
			dto.setId(authentication.getUsername());
			MemberDTO memberDTO = this.memberService.findById(authentication.getUsername(), 0);
			dto.setReplyer(memberDTO.getName());
			dto.setRno(rno);
			replyService.update(dto);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/{rno}")
	public String delete(@PathVariable("rno") int rno) {
		try {
			replyService.delete(rno);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
