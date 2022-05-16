package com.hyundai.minihompy.controller;

import com.hyundai.minihompy.domain.LoginDto;
import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.security.jwt.JwtFilter;
import com.hyundai.minihompy.security.jwt.TokenProvider;
import com.hyundai.minihompy.service.MemberService;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MemberController {

  private final MemberService memberService;
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public MemberController(
    TokenProvider tokenProvider,
    AuthenticationManagerBuilder authenticationManagerBuilder,
    MemberService memberService
  ) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.memberService = memberService;
  }

  // 회원 가입 후 로그인 창으로 이동하기
  @PostMapping(path = "/members/signup")
  public String signUp(@RequestBody MemberDTO memberDTO) throws SQLException {
    memberService.insertMember(memberDTO);
    return "member/login";
  }
}
