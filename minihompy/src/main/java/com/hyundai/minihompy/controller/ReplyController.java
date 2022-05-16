package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.minihompy.domain.ReplyDTO;
import com.hyundai.minihompy.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
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
	
	@PostMapping("")
	public String insert(@RequestBody ReplyDTO dto) {
		try {
			dto.setId("user1");
			dto.setReplyer("김땡땡");
			replyService.insert(dto);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public String update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto) {
		try {
			dto.setRno(rno);
			replyService.update(dto);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
