package com.hyundai.minihompy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
