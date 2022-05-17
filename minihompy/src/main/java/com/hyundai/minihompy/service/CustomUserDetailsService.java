package com.hyundai.minihompy.service;

import com.hyundai.minihompy.dao.MemberDAO;
import com.hyundai.minihompy.domain.MemberDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*************************************************************
파일명: CustomUserDetailsService.java
기능: Security 관련 Service
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberDAO memberDAO;

  public CustomUserDetailsService(MemberDAO memberDAO) {
    this.memberDAO = memberDAO;
  }

  @SneakyThrows
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    MemberDTO member = memberDAO.findById(username, 0);
    return createUser(username, member);
  }

  private User createUser(String username, MemberDTO memberDTO){

    List<GrantedAuthority> grantedAuthorities = memberDTO.getAuthorities().stream()
      .map(authority -> new SimpleGrantedAuthority(authority.getRole_set()))
      .collect(Collectors.toList());

    return new User(memberDTO.getId(), memberDTO.getPassword(), grantedAuthorities);
  }

}
