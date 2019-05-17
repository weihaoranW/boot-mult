package com.wei.controller;

import com.wei.util.jwt.JwtConst;
import com.wei.util.jwt.JwtToken;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "这是测试方法")
@RestController
@RequestMapping("/Test")
public class TestJWTController {

@ApiOperation(value = "传入用户名生成token!")
@GetMapping("/jwt/{userCode}")
public String testGenerateJWT(
 @PathVariable("userCode") String userCode
) {
 return JwtToken.create(userCode);
}

@ApiOperation(value = "传入用户名生成token!")
@GetMapping("/jwtParse/{token}")
public String testjwt_parse(
 @PathVariable("token") String token
) {
 if (token != null) {
  String user = Jwts.parser()
   .setSigningKey(JwtConst.sign_key)
   .parseClaimsJws(token.replace(JwtConst.token_prefix, ""))
   .getBody()
   .getSubject();

  return "user:" + user + "---body:" + Jwts.parser()
   .setSigningKey(JwtConst.sign_key)
   .parseClaimsJws(token.replace(JwtConst.token_prefix, ""))
   .getBody();
 }
 return "no parse";
}
}