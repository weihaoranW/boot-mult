package com.wei.util.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtToken {
public static String create(String userCode) {
 String token = Jwts.builder()
  .setSubject(userCode)
  .setExpiration(new Date(System.currentTimeMillis() + JwtConst.expired_ms))
  .signWith(SignatureAlgorithm.HS512, JwtConst.sign_key)
  .compact();

 return JwtConst.token_prefix + token;
}

//根据传入的token，解析出来 用户名称（代码）
public static String getUser(String token) {
 String user = Jwts.parser()
  .setSigningKey(JwtConst.sign_key)
  .parseClaimsJws(token.replace(JwtConst.token_prefix, ""))
  .getBody()
  .getSubject();

 return user;
}

}
