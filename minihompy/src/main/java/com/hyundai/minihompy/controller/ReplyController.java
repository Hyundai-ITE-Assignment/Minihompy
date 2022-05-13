package com.hyundai.minihompy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@Controller
@Log4j2
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("list/{bno}")
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bno") int bno) {
		ResponseEntity<List<ReplyDTO>> entry = null;
		
		try {
			entry = new ResponseEntity<List<ReplyDTO>>(replyService.getList(bno), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ReplyDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entry;
	}
	
	@PostMapping("")
	public ResponseEntity<String> insert(@RequestBody ReplyDTO dto) {
		ResponseEntity<String> entry = null;
		
		try {
			dto.setId("user1");
			replyService.insert(dto);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entry;
	}
	
	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto) {
		ResponseEntity<String> entry = null;
		
		try {
			dto.setRno(rno);
			replyService.update(dto);
			log.info("update");
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entry;
	}
	
	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
		ResponseEntity<String> entry = null;
		
		try {
			replyService.delete(rno);
			log.info("delete");
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entry;
	}
}
