package com.hyundai.minihompy.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
파일명: GuestbookDTO.java
기능: 방명록 DTO
작성자: 박주영

[코멘트: X]
*************************************************************/
@Data
@Getter
@Setter
public class GuestbookDTO {
	private long gno;
	private String id;
	private String name;
	private String content;
	private Date regdate;
}
