package com.hyundai.minihompy.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*************************************************************
 파일명: JwtSecurityConfig.java
 기능: JWT관련 환경 설정 등록
 작성자: 유지훈

 [코멘트: X]
 *************************************************************/
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private TokenProvider tokenProvider;

  public JwtSecurityConfig(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  //TokenProvider, JwtFilter를 적용할때 사용
  @Override
  public void configure(HttpSecurity httpSecurity){ //security로직에 필터를 등록한다.
    JwtFilter customFilter = new JwtFilter(tokenProvider);
    httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
