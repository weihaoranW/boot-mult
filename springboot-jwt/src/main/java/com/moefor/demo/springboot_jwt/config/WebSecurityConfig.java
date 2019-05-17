package com.moefor.demo.springboot_jwt.config;

import com.moefor.demo.springboot_jwt.filter.JwtAuthenticationFilter;
import com.moefor.demo.springboot_jwt.filter.JwtLoginFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
 httpSecurity.cors()
  .and().csrf().disable()
  .sessionManagement()
  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  .and()
  .authorizeRequests()
  .antMatchers(HttpMethod.POST, "/user/signup")
  .permitAll()
  .anyRequest().authenticated()
  .and()
  .addFilter(new JwtAuthenticationFilter(authenticationManager()))
  .addFilterAfter(new JwtLoginFilter(authenticationManager()), JwtAuthenticationFilter.class);
}

}
