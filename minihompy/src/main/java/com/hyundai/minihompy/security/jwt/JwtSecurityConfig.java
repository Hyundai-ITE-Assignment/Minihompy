package com.hyundai.minihompy.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  //TokenProvider, JwtFilter를 적용할때 사용

  private TokenProvider tokenProvider;

  public JwtSecurityConfig(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void configure(HttpSecurity httpSecurity){ //security로직에 필터를 등록한다.
    JwtFilter customFilter = new JwtFilter(tokenProvider);
    httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
