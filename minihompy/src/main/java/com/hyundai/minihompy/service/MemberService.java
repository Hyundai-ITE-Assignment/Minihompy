package com.hyundai.minihompy.service;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Param;

public interface MemberService {

    void insertMember(MemberDTO memberDTO) throws SQLException;

    MemberDTO findById(String id, int social);

    void updateMember(MemberDTO memberDTO);

    void deleteMember(String id);
}
