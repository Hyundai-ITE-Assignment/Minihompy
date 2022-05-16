package com.hyundai.minihompy.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TokenProvider implements InitializingBean {

  private static final String AUTHORITIES_KEY = "auth";

  private final String secret;
  private final long tokenValidityInMilliseconds;

  private Key key;

  public TokenProvider(
    @Value("${jwt.secret}") String secret,
    @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds
  ) {
    this.secret = secret;
    this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    //afterPropertiesSet을 override한 이유는
    // 빈이 생성이 되고 의존성 주입을 받은 후에 secret값을 Base64 Decode해서 key변수에 할당하기 위해 사용
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(Authentication authentication) { //토큰 생성 메서드
    String authorities = authentication.getAuthorities().stream() //권한들 가져오기
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + this.tokenValidityInMilliseconds); //토큰 만료시간 설정

    return Jwts.builder()  //토큰 생성해준다.
      .setSubject(authentication.getName())
      .claim(AUTHORITIES_KEY, authorities)
      .signWith(key, SignatureAlgorithm.HS512)
      .setExpiration(validity)
      .compact();
  }

  //토큰에 담겨있는 정보를 이용해 Authentication 객체를 리턴하는 메소드 생성
  public Authentication getAuthentication(String token) {
    Claims claims = Jwts   //토큰으로 claim
      .parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();

    Collection<? extends GrantedAuthority> authorities =
      Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities); //유저 생성

    return new UsernamePasswordAuthenticationToken(principal, token, authorities); //인증 객체 생성 리턴
  }

  public boolean validateToken(String token) { //토큰의 유효성 검증을 수행하는 validateToken 메서드
      try {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return true;
      } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
        log.info("잘못된 JWT 서명입니다.");
      } catch (ExpiredJwtException e) {
        log.info("만료된 JWT 토큰입니다.");
      } catch (UnsupportedJwtException e) {
        log.info("지원되지 않는 JWT 토큰입니다.");
      } catch (IllegalArgumentException e) {
        log.info("JWT 토큰이 잘못되었습니다.");
      }
      return false;
    }
  }
