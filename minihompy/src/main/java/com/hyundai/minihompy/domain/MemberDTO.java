package com.hyundai.minihompy.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

    private String id;
    private String name;
    private String password;
    private String email;
    private int dotori;
    private int from_social;
    private LocalDate moddate;
    private LocalDate regdate;

    private List<MemberRoleSet> authorities;
}
