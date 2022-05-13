package com.hyundai.minihompy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private long bno;
	private String id;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	private int readcount;
	private String password;
}
