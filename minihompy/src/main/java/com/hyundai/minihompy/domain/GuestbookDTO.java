package com.hyundai.minihompy.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
