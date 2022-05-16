package com.hyundai.minihompy.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRoleSet implements Serializable {
    private String member_id;
    private String role_set;
}
