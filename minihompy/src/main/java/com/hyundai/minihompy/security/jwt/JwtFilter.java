package com.hyundai.minihompy.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

@Slf4j
public class JwtFilter extends GenericFilter {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private TokenProvider tokenProvider;

  public JwtFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void doFilter(  //dofilter는 토큰의 인증정보를 securityContext에 저장하는 역할 수행
    ServletRequest servletRequest,
    ServletResponse servletResponse,
    FilterChain filterChain
  ) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String jwt = resolveToken(httpServletRequest); //토큰을 받아와서
    String requestURI = httpServletRequest.getRequestURI();

    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
      Authentication authentication = tokenProvider.getAuthentication(jwt);
      SecurityContextHolder.getContext().setAuthentication(authentication); //유효성 검증을 하고 정상 토큰이면 Security Context에 저장
      log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
    } else {
      log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
