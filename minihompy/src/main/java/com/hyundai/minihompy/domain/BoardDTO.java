package com.hyundai.minihompy.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
파일명: BoardDTO.java
기능: 게시글 DTO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Data
@Getter
@Setter
public class BoardDTO {
	private long bno;
	private String id;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	private MultipartFile attach;
	private String attachoname;
	private String attachsname;
	private String attachtype;
	private int hitcount;
}
