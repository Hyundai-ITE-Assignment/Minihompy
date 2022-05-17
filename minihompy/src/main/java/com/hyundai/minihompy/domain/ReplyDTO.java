package com.hyundai.minihompy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************************************
파일명: ReplyDTO.java
기능: 댓글 관련 DTO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Getter
@Setter
@ToString
public class ReplyDTO {
	private int rno;
	private int bno;
	private String id;
	private String replyer;
	private String replytext;
	private Date regdate;
	private Date updatedate;
}
