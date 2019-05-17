package com.moefor.demo.springboot_jwt.filter;

import io.jsonwebtoken.Jwts;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Logger
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//private static final Logger log = LoggerFactory
// .getLogger(JwtAuthenticationFilter.class);

public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
 super(authenticationManager);
}

@Override
protected void doFilterInternal(HttpServletRequest req,
                                HttpServletResponse res,
                                FilterChain chain) throws IOException, ServletException {
 String header = req.getHeader("Authorization");
 logger.info("-----------------------");
 if (header == null || !header.startsWith("Bearer ")) {
  chain.doFilter(req, res);
  return;
 }

 UsernamePasswordAuthenticationToken auth = getAuthentication(req);

 SecurityContextHolder.getContext().setAuthentication(auth);
 chain.doFilter(req, res);
}

private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
 logger.info("-------UsernamePasswordAuthenticationToken----------------");
 String token = req.getHeader("Authorization");
 if (token != null) {
  // parse the token.
  String user = Jwts.parser()
   .setSigningKey("JwtSecret")
   .parseClaimsJws(token.replace("Bearer ", ""))
   .getBody()
   .getSubject();

  if (user != null) {
   return new UsernamePasswordAuthenticationToken(
    user,
    null,
    new ArrayList<>()
   );
  }
  return null;
 }
 return null;
}
}
