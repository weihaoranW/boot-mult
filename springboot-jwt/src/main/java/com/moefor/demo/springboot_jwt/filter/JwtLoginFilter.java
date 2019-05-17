package com.moefor.demo.springboot_jwt.filter;

import com.moefor.demo.springboot_jwt.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Logger
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

private AuthenticationManager authenticationManager;

public JwtLoginFilter(AuthenticationManager authenticationManager) {
 super(new AntPathRequestMatcher("/user/sigin", "POST"));
 logger.info("---LoginFilter Init-----------------------");
 this.authenticationManager = authenticationManager;
}

@Override
public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
 logger.info("---LoginFilter ->attemptAuthentication-----------------------");
 User user = null;
 try {
  user = new ObjectMapper().readValue(req.getInputStream(), User.class);
 } catch (IOException e) {
  e.printStackTrace();
 }
 return authenticationManager.authenticate(
  new UsernamePasswordAuthenticationToken(
   user.getUsername(),
   user.getPassword(),
   new ArrayList<>())
 );
}

@Override
protected void successfulAuthentication(HttpServletRequest req,
                                        HttpServletResponse res,
                                        FilterChain chain,
                                        Authentication auth) throws IOException, ServletException {
 logger.info("-----#####--------SUCCESSFUL---------------------");
 String token = Jwts.builder()
  .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
  .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
  .signWith(SignatureAlgorithm.HS512, "JwtSecret")
  .compact();
 res.addHeader("Authorization", "Bearer " + token);
}

}
